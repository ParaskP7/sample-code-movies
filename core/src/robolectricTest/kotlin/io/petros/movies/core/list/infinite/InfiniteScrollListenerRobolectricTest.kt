package io.petros.movies.core.list.infinite

import androidx.recyclerview.widget.LinearLayoutManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.app.TestApp
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class InfiniteScrollListenerRobolectricTest {

    private val layoutManagerMock = mockk<LinearLayoutManager>()
    private val listenerMock = mockk<InfiniteScrollListener.Listener>()
    private val testedClass = InfiniteScrollListener(layoutManagerMock, listenerMock)

    @Test
    fun `given loading, then when on scrolled is triggered, then the listener will not load more`() {
        onScrolled(true, 2, 2)

        verify(exactly = 0) { listenerMock.loadMore() }
    }

    @Test
    fun `given no next page, then when on scrolled is triggered, then the listener will not load more`() {
        onScrolled(false, null, 2)

        verify(exactly = 0) { listenerMock.loadMore() }
    }

    @Test
    fun `given visible threshold is not breached, then when on scrolled is triggered, then listener will not load more`() {
        onScrolled(false, 2, 0)

        verify(exactly = 0) { listenerMock.loadMore() }
    }

    @Test
    fun `given visible threshold is breached, then when on scrolled is triggered, then listener will load more`() {
        onScrolled(false, 2, 2)

        verify { listenerMock.loadMore() }
    }

    @Test
    fun `given zero vertical scroll, then when on scrolled is triggered, then listener will not load more`() {
        onScrolled(false, 2, 2, 0)

        verify(exactly = 0) { listenerMock.loadMore() }
    }

    /* HELPER FUNCTIONS */

    private fun onScrolled(
        isLoading: Boolean,
        nextPage: Int?,
        lastVisibleItemPosition: Int,
        dy: Int = 1
    ) {
        every { listenerMock.isLoading() } returns isLoading
        every { listenerMock.nextPage() } returns nextPage
        every { layoutManagerMock.itemCount } returns 20
        every { layoutManagerMock.findLastVisibleItemPosition() } returns lastVisibleItemPosition

        testedClass.onScrolled(mockk(), 0, dy)
    }

}
