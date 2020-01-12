package io.petros.movies.presentation.feature.common.view

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.utils.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.test.domain.NEXT_PAGE
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class InfiniteRecyclerViewRobolectricTest {

    private val context = provideContext()

    private var adapterMock = mockk<InfiniteAdapter<Movie>>()

    private lateinit var testedClass: InfiniteRecyclerView
    private var listenerMock = mockk<InfiniteRecyclerView.Listener>()

    @Before
    fun setUp() {
        testedClass = InfiniteRecyclerView(context)
        testedClass.adapter = adapterMock
        testedClass.listener = listenerMock
    }

    @Test
    fun `when requesting next page, then adapter gets asked for the page`() {
        testedClass.nextPage()

        verify { adapterMock.nextPage() }
    }

    @Test
    fun `when load more is triggered, then infinite scrolling listener's load data is triggered for next page`() {
        every { adapterMock.nextPage() } returns NEXT_PAGE

        testedClass.loadMore()

        verify { listenerMock.loadData(NEXT_PAGE) }
    }

    @Test
    fun `when requesting loading status, then adapter gets asked for the loading status`() {
        testedClass.isLoading()

        verify { adapterMock.isLoading() }
    }

}
