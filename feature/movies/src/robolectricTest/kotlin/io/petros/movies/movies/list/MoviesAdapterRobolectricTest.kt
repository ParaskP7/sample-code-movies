package io.petros.movies.movies.list

import androidx.recyclerview.widget.RecyclerView
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.core.list.adapter.AdapterStatus
import io.petros.movies.core.list.holder.ErrorViewHolder
import io.petros.movies.core.list.holder.ProgressViewHolder
import io.petros.movies.movies.list.MoviesAdapter.Companion.VIEW_TYPE_ERROR
import io.petros.movies.movies.list.MoviesAdapter.Companion.VIEW_TYPE_MOVIE
import io.petros.movies.movies.list.MoviesAdapter.Companion.VIEW_TYPE_PROGRESS
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
class MoviesAdapterRobolectricTest {

    private val recyclerView = RecyclerView(context())

    private val items = listOf(movie(id = 1), movie(id = 2), movie(id = 3))

    private val testedClass = MoviesAdapter(ArrayList(items)).also {
        it.callback = mockk()
    }

    /* STATUS */

    @Test
    fun `when adapter is set, then status is idle`() {
        expect { that(testedClass.status).isEqualTo(AdapterStatus.IDLE) }
    }

    /* ITEMS */

    @Test
    fun `when adapter is set, then all items are used`() {
        expect { that(testedClass.itemCount).isEqualTo(items.size) }
    }

    /* VIEW HOLDER */

    @Test
    fun `when creating a view holder for a progress item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_PROGRESS)

        expect { that(viewHolder.javaClass.name).isEqualTo(ProgressViewHolder::class.java.name) }
    }

    @Test
    fun `when creating a view holder for a movie item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)

        expect { that(viewHolder.javaClass.name).isEqualTo(MovieViewHolder::class.java.name) }
    }

    @Test
    fun `when creating a view holder for an error item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_ERROR)

        expect { that(viewHolder.javaClass.name).isEqualTo(ErrorViewHolder::class.java.name) }
    }

    @Test
    fun `given a movie view type, when binding a view holder, then a movie item is bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)
        val position = 1
        val viewHolderMock = mockk<MovieViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify { viewHolderMock.bind(items[position]) }
    }

    @Test
    fun `given a non-movie view type, when binding a view holder, then a movie item is not bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_PROGRESS)
        testedClass.status = AdapterStatus.LOADING
        val position = items.size
        val viewHolderMock = mockk<MovieViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify(exactly = 0) { viewHolderMock.bind(any()) }
    }

    /* NAVIGATION */

    @Test
    fun `when getting the item view type of a movie item, then a movie view type is returned`() {
        val result = testedClass.getItemViewType(1)

        expect { that(result).isEqualTo(VIEW_TYPE_MOVIE) }
    }

    @Test
    fun `given at last position and idle, when getting the item view type, then a movie item view type is returned`() {
        testedClass.status = AdapterStatus.IDLE

        val result = testedClass.getItemViewType(items.size)

        expect { that(result).isEqualTo(VIEW_TYPE_MOVIE) }
    }

    @Test
    fun `given at last position and loading, when getting the item view type, then a progress view type is returned`() {
        testedClass.status = AdapterStatus.LOADING

        val result = testedClass.getItemViewType(items.size)

        expect { that(result).isEqualTo(VIEW_TYPE_PROGRESS) }
    }

    @Test
    fun `given at last position and error, when getting the item view type, then an error view type is returned`() {
        testedClass.status = AdapterStatus.ERROR

        val result = testedClass.getItemViewType(items.size)

        expect { that(result).isEqualTo(VIEW_TYPE_ERROR) }
    }

}
