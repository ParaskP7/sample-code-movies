package io.petros.movies.presentation.feature.common.view

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.RobolectricTestProvider.Companion.provideContext
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
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
        setUpMocks()
        testedClass.adapter = adapterMock
        testedClass.listener = listenerMock
    }

    private fun setUpMocks() {
        every { adapterMock.registerAdapterDataObserver(any()) } just Runs
        every { adapterMock.onAttachedToRecyclerView(any()) } just Runs
        every { adapterMock.hasStableIds() } returns true
    }

    @Test
    fun `When requesting next page, then adapter gets asked for the page`() {
        every { adapterMock.nextPage() } returns NEXT_PAGE

        testedClass.nextPage()

        verify { adapterMock.nextPage() }
    }

    @Test
    fun `When load more is triggered, then infinite scrolling listener's load data is triggered for next page`() {
        every { adapterMock.nextPage() } returns NEXT_PAGE
        every { listenerMock.loadData(NEXT_PAGE) } just Runs

        testedClass.loadMore()

        verify { listenerMock.loadData(NEXT_PAGE) }
    }

    @Test
    fun `When requesting loading status, then adapter gets asked for the loading status`() {
        every { adapterMock.isLoading() } returns true

        testedClass.isLoading()

        verify { adapterMock.isLoading() }
    }

}
