package io.petros.movies.core.list.infinite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.mockk.mockk
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.android_test.runner.CustomRobolectricTestRunner
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

@RunWith(CustomRobolectricTestRunner::class)
class InfiniteAdapterRobolectricTest {

    companion object {

        private const val SECOND_PAGE = 2

    }

    private val context = context()
    private val recyclerView = RecyclerView(context)

    private val firstPageItems = listOf(movie(id = 1), movie(id = 2), movie(id = 3))
    private val secondPageItems = listOf(movie(id = 4), movie(id = 5), movie(id = 6))

    private val testedClass = object : InfiniteAdapter<Movie>() {
        override fun isLoading() = false

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = mockk<RecyclerView.ViewHolder>()

        override fun getItemCount() = 0

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = Unit
    }

    /* CONTEXT */

    @Test
    fun `when attaching to recycler view, then context is set`() {
        expect { that(testedClass.context).isNull() }

        testedClass.onAttachedToRecyclerView(recyclerView)

        expect { that(testedClass.context).isEqualTo(context) }
    }

    @Test
    fun `when detaching from recycler view, then context is unset`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        expect { that(testedClass.context).isEqualTo(context) }

        testedClass.onDetachedFromRecyclerView(recyclerView)

        expect { that(testedClass.context).isNull() }
    }

    /* ITEMS */

    @Test
    fun `given first page load, when setting items to adapter, then the first page items are set`() {
        expect { that(testedClass.items()).isEqualTo(emptyList()) }

        setItems(PaginationData(), SECOND_PAGE, firstPageItems)

        expect { that(testedClass.items()).isEqualTo(firstPageItems) }
    }

    @Test
    fun `given second page load, when setting items to adapter, then new items are appended on the current items`() {
        val paginationData = setItems(PaginationData(), SECOND_PAGE, firstPageItems)
        expect { that(testedClass.items()).isEqualTo(firstPageItems) }

        setItems(paginationData, SECOND_PAGE + 1, secondPageItems)

        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }
    }

    @Test
    fun `given page reload, when setting items to adapter, then all page items are restored`() {
        var paginationData = setItems(PaginationData(), SECOND_PAGE, firstPageItems)
        paginationData = setItems(paginationData, SECOND_PAGE + 1, secondPageItems)
        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }

        testedClass.setItems(paginationData, true)

        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }
    }

    /* HELPER FUNCTIONS */

    private fun setItems(
        paginationData: PaginationData<Movie>,
        nextPage: Int,
        movies: List<Movie>,
    ): PaginationData<Movie> {
        val items = PaginationData(
            paginationData.allPageItems + movies,
            MoviesPage(nextPage, movies),
            nextPage,
        )
        testedClass.setItems(items, false)
        return items
    }

}
