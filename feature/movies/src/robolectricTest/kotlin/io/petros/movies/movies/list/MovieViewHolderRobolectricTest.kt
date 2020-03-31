package io.petros.movies.movies.list

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.movies.listener.MovieCallback
import io.petros.movies.movies.view.MovieItemView
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieViewHolderRobolectricTest {

    private val movie = movie()

    private val itemViewMock = mockk<MovieItemView>()
    private val callbackMock = mockk<MovieCallback>()
    private val testedClass = MovieViewHolder(itemViewMock, callbackMock)

    @Test
    fun `when the view holder binds a movie, then the item view is bind with a movie`() {
        testedClass.bind(movie)

        verify { itemViewMock.bind(movie) }
    }

    @Test
    fun `when the view holder binds a movie, then the item view is bind with a movie callback`() {
        testedClass.bind(movie)

        verify { itemViewMock.bindCallback(movie, callbackMock) }
    }

}
