package io.petros.movies.movies.list

import androidx.recyclerview.widget.RecyclerView
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.core.list.ErrorViewHolder
import io.petros.movies.core.list.ProgressViewHolder
import io.petros.movies.movies.list.MoviesAdapter.Companion.VIEW_TYPE_ERROR
import io.petros.movies.movies.list.MoviesAdapter.Companion.VIEW_TYPE_MOVIE
import io.petros.movies.movies.list.MoviesAdapter.Companion.VIEW_TYPE_PROGRESS
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue

@RunWith(RobolectricTestRunner::class)
class MoviesAdapterRobolectricTest {

    private val recyclerView = RecyclerView(context())

    private val items = listOf(movie(id = 1), movie(id = 2), movie(id = 3))

    private val testedClass = MoviesAdapter(ArrayList(items)).also {
        it.itemCallback = mockk()
    }

    /* STATUS */

    @Test
    fun `when adapter is set, then status is idle`() {
        expect { that(testedClass.status).isEqualTo(AdapterStatus.IDLE) }
    }

    @Test
    fun `when adapter is set, then is loading is false`() {
        expect { that(testedClass.isLoading()).isFalse() }
    }

    /* IS LOADING */

    @Test
    fun `given status change to idle, when is loading is queried, then is loading is false`() {
        testedClass.status = AdapterStatus.IDLE

        expect { that(testedClass.isLoading()).isFalse() }
    }

    @Test
    fun `given status change to loading, when is loading is queried, then is loading is true`() {
        testedClass.status = AdapterStatus.LOADING

        expect { that(testedClass.isLoading()).isTrue() }
    }

    @Test
    fun `given status change to error, when is loading is queried, then is loading is false`() {
        testedClass.status = AdapterStatus.ERROR

        expect { that(testedClass.isLoading()).isFalse() }
    }

    /* ITEMS */

    @Test
    fun `when adapter is set, then all items are used`() {
        expect { that(testedClass.itemCount).isEqualTo(items.size) }
    }

    /* CONTEXT */

    @Test(expected = IllegalArgumentException::class)
    fun `given not attached to recycle view, when creating a view holder, then throw illegal argument exception`() {
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given detached from recycle view, when creating a view holder, then throw illegal argument exception`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onDetachedFromRecyclerView(recyclerView)

        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)
    }

    /* VIEW HOLDER */

    @Test
    fun `given a movie view type, when creating a view holder, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)

        expect { that(viewHolder.javaClass.name).isEqualTo(MovieViewHolder::class.java.name) }
    }

    @Test
    fun `given a progress view type, when creating a view holder, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_PROGRESS)

        expect { that(viewHolder.javaClass.name).isEqualTo(ProgressViewHolder::class.java.name) }
    }

    @Test
    fun `given an error view type, when creating a view holder, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_ERROR)

        expect { that(viewHolder.javaClass.name).isEqualTo(ErrorViewHolder::class.java.name) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given an unknown view type, when creating a view holder, then throw illegal argument exception`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        testedClass.onCreateViewHolder(mockk(), 2)
    }

    @Test
    fun `given a movie view type, when binding a view holder, then a movie item is bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)
        testedClass.status = AdapterStatus.IDLE
        val position = 0
        val viewHolderMock = mockk<MovieViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify { viewHolderMock.bind(items[position]) }
    }

    @Test
    fun `given a movie view type at last position, when binding a view holder, then a movie item is bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)
        testedClass.status = AdapterStatus.IDLE
        val position = items.size - 1
        val viewHolderMock = mockk<MovieViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify { viewHolderMock.bind(items[position]) }
    }

    @Test
    fun `given a progress view type at last position, when binding a view holder, then a movie item is not bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_PROGRESS)
        testedClass.status = AdapterStatus.LOADING
        val position = items.size
        val viewHolderMock = mockk<MovieViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify(exactly = 0) { viewHolderMock.bind(any()) }
    }

    @Test
    fun `given an error view type at last position, when binding a view holder, then a movie item is not bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_ERROR)
        testedClass.status = AdapterStatus.ERROR
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
