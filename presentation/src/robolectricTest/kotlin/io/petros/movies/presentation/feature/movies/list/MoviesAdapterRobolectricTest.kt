package io.petros.movies.presentation.feature.movies.list

import androidx.recyclerview.widget.RecyclerView
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.movies.presentation.feature.common.list.holder.ErrorViewHolder
import io.petros.movies.presentation.feature.common.list.holder.ProgressViewHolder
import io.petros.movies.presentation.feature.movies.list.MoviesAdapter.Companion.VIEW_TYPE_ERROR
import io.petros.movies.presentation.feature.movies.list.MoviesAdapter.Companion.VIEW_TYPE_MOVIE
import io.petros.movies.presentation.feature.movies.list.MoviesAdapter.Companion.VIEW_TYPE_PROGRESS
import io.petros.movies.presentation.utils.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.test.domain.provideMovie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isEqualTo

@RunWith(RobolectricTestRunner::class)
class MoviesAdapterRobolectricTest {

    private val context = provideContext()
    private val recyclerView = RecyclerView(context)

    private val items = listOf(provideMovie(id = 1), provideMovie(id = 2), provideMovie(id = 3))

    private lateinit var testedClass: MoviesAdapter

    @Before
    fun setUp() {
        testedClass = MoviesAdapter(ArrayList(items))
        testedClass.callback = mockk()
    }

    /* STATUS */

    @Test
    fun `When adapter is set, then status is idle`() {
        expect { that(testedClass.status).isEqualTo(AdapterStatus.IDLE) }
    }

    /* ITEMS */

    @Test
    fun `When adapter is set, then all items are used`() {
        expect { that(testedClass.itemCount).isEqualTo(items.size) }
    }

    /* VIEW HOLDER */

    @Test
    fun `When creating a view holder for a progress item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_PROGRESS)

        expect { that(viewHolder.javaClass.name).isEqualTo(ProgressViewHolder::class.java.name) }
    }

    @Test
    fun `When creating a view holder for a movie item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)

        expect { that(viewHolder.javaClass.name).isEqualTo(MovieViewHolder::class.java.name) }
    }

    @Test
    fun `When creating a view holder for an error item, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_ERROR)

        expect { that(viewHolder.javaClass.name).isEqualTo(ErrorViewHolder::class.java.name) }
    }

    @Test
    fun `Given a movie view type, when binding a view holder, then a movie item is bind`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onCreateViewHolder(mockk(), VIEW_TYPE_MOVIE)
        val position = 1
        val viewHolderMock = mockk<MovieViewHolder>()

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify { viewHolderMock.bind(items[position]) }
    }

    @Test
    fun `Given a non-movie view type, when binding a view holder, then a movie item is not bind`() {
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
    fun `When getting the item view type of a movie item, then a movie view type is returned`() {
        val result = testedClass.getItemViewType(1)

        expect { that(result).isEqualTo(VIEW_TYPE_MOVIE) }
    }

    @Test
    fun `Given at last position and idle, when getting the item view type, then a movie item view type is returned`() {
        testedClass.status = AdapterStatus.IDLE

        val result = testedClass.getItemViewType(items.size)

        expect { that(result).isEqualTo(VIEW_TYPE_MOVIE) }
    }

    @Test
    fun `Given at last position and loading, when getting the item view type, then a progress view type is returned`() {
        testedClass.status = AdapterStatus.LOADING

        val result = testedClass.getItemViewType(items.size)

        expect { that(result).isEqualTo(VIEW_TYPE_PROGRESS) }
    }

    @Test
    fun `Given at last position and error, when getting the item view type, then an error view type is returned`() {
        testedClass.status = AdapterStatus.ERROR

        val result = testedClass.getItemViewType(items.size)

        expect { that(result).isEqualTo(VIEW_TYPE_ERROR) }
    }

}
