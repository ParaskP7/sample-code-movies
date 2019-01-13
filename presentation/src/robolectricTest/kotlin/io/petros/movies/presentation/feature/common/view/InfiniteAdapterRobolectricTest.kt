package io.petros.movies.presentation.feature.common.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhaarman.mockitokotlin2.mock
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

@RunWith(RobolectricTestRunner::class)
class InfiniteAdapterRobolectricTest {

    private val context = provideContext()
    private val recyclerView = RecyclerView(context)

    private val firstPageItems = listOf(provideMovie(id = 1), provideMovie(id = 2), provideMovie(id = 3))
    private val secondPageItems = listOf(provideMovie(id = 4), provideMovie(id = 5), provideMovie(id = 6))
    private val anotherPageItems = listOf(provideMovie(id = 7), provideMovie(id = 8), provideMovie(id = 9))

    private lateinit var testedClass: InfiniteAdapter<Movie>

    @Before
    fun setUp() {
        testedClass = object : InfiniteAdapter<Movie>() {
            override fun isLoading() = false

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = mock<RecyclerView.ViewHolder>()

            override fun getItemCount() = 0

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
        }
    }

    /* CONTEXT */

    @Test
    fun `When attaching to recycler view, then context is set`() {
        expect { that(testedClass.context).isNull() }

        testedClass.onAttachedToRecyclerView(recyclerView)

        expect { that(testedClass.context).isEqualTo(context) }
    }

    @Test
    fun `When detaching from recycler view, then context is unset`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        expect { that(testedClass.context).isEqualTo(context) }

        testedClass.onDetachedFromRecyclerView(recyclerView)

        expect { that(testedClass.context).isNull() }
    }

    /* ITEMS */

    @Test
    fun `Given first page load, when setting items to adapter, then the first page items are set`() {
        expect { that(testedClass.items()).isEqualTo(emptyList()) }

        testedClass.setItems(PaginationData<Movie>().addPage(MoviesResultPage(NEXT_PAGE, firstPageItems)))

        expect { that(testedClass.items()).isEqualTo(firstPageItems) }
    }

    @Test
    fun `Given second page load, when setting items to adapter, then new items are appended on the current items`() {
        val paginationData = PaginationData<Movie>()
        paginationData.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.setItems(paginationData)
        expect { that(testedClass.items()).isEqualTo(firstPageItems) }
        paginationData.addPage(MoviesResultPage(NEXT_PAGE + 1, secondPageItems))

        testedClass.setItems(paginationData)

        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }
    }

    @Test
    fun `Given another page reload, when setting items to adapter, then this another page items are reloaded`() {
        testedClass.setItems(PaginationData<Movie>().addPage(MoviesResultPage(NEXT_PAGE, firstPageItems)))
        expect { that(testedClass.items()).isEqualTo(firstPageItems) }

        testedClass.setItems(PaginationData<Movie>().addPage(MoviesResultPage(NEXT_PAGE, anotherPageItems)))

        expect { that(testedClass.items()).isEqualTo(anotherPageItems) }
    }

    @Test
    fun `Given page restore, when setting items to adapter, then all page items are restored`() {
        val paginationData = PaginationData<Movie>()
        paginationData.addPage(MoviesResultPage(NEXT_PAGE, firstPageItems))
        testedClass.setItems(paginationData)
        paginationData.addPage(MoviesResultPage(NEXT_PAGE + 1, secondPageItems))
        testedClass.setItems(paginationData)
        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }

        testedClass.setItems(paginationData)

        expect { that(testedClass.items()).isEqualTo(firstPageItems + secondPageItems) }
    }

}
