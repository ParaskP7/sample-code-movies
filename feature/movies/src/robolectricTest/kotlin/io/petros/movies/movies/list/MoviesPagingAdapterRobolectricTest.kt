package io.petros.movies.movies.list

import androidx.recyclerview.widget.RecyclerView
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.petros.movies.androidtest.context.TestContextProvider.context
import io.petros.movies.androidtest.runner.CustomRobolectricTestRunner
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

@RunWith(CustomRobolectricTestRunner::class)
class MoviesPagingAdapterRobolectricTest {

    private val context = context()
    private val recyclerView = RecyclerView(context())

    private val testedClass = spyk(
        MoviesPagingAdapter().also {
            it.itemCallback = mockk()
        }
    )

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

    /* VIEW HOLDER */

    @Test(expected = IllegalArgumentException::class)
    fun `given not attached to recycle view, when creating a view holder, then throw illegal argument exception`() {
        testedClass.onCreateViewHolder(mockk(), 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given detached from recycle view, when creating a view holder, then throw illegal argument exception`() {
        testedClass.onAttachedToRecyclerView(recyclerView)
        testedClass.onDetachedFromRecyclerView(recyclerView)

        testedClass.onCreateViewHolder(mockk(), 0)
    }

    @Test
    fun `when creating a view holder, then the correct view holder is returned`() {
        testedClass.onAttachedToRecyclerView(recyclerView)

        val viewHolder = testedClass.onCreateViewHolder(mockk(), 0)

        expect { that(viewHolder.javaClass.name).isEqualTo(MovieViewHolder::class.java.name) }
    }

    @Test
    fun `when binding a view holder, then a movie item is bind`() {
        val position = 0
        val movie = movie()
        val viewHolderMock = mockk<MovieViewHolder>()
        every { testedClass invoke PROTECTED_ADAPTER_GET_ITEM_FUNCTION withArguments listOf(position) } returns movie

        testedClass.onBindViewHolder(viewHolderMock, position)

        verify { viewHolderMock.bind(movie) }
    }

    companion object {

        private const val PROTECTED_ADAPTER_GET_ITEM_FUNCTION = "getItem"

    }

}
