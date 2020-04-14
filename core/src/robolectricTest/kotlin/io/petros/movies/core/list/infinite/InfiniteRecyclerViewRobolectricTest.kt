package io.petros.movies.core.list.infinite

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.domain.model.movie.Movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class InfiniteRecyclerViewRobolectricTest {

    companion object {

        private const val SECOND_PAGE = 2

    }

    private var adapterMock = mockk<InfiniteAdapter<Movie>>()

    private var listenerMock = mockk<InfiniteRecyclerView.Listener>()
    private val testedClass = InfiniteRecyclerView(context()).also {
        it.adapter = adapterMock
        it.listener = listenerMock
    }

    @Test
    fun `when requesting next page, then adapter gets asked for the page`() {
        testedClass.nextPage()

        verify { adapterMock.nextPage() }
    }

    @Test
    fun `when load more is triggered, then infinite scrolling listener's load data is triggered for next page`() {
        every { adapterMock.nextPage() } returns SECOND_PAGE

        testedClass.loadMore()

        verify { listenerMock.loadData(SECOND_PAGE) }
    }

    @Test
    fun `when requesting loading status, then adapter gets asked for the loading status`() {
        testedClass.isLoading()

        verify { adapterMock.isLoading() }
    }

}
