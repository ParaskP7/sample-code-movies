package io.petros.movies.movies.list

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.list.item.MovieItemView
import io.petros.movies.test.domain.movie
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieViewHolderRobolectricTest {

    private val movie = movie()

    private val itemViewMock = mockk<MovieItemView>()
    private val itemCallbackMock = mockk<MovieItemCallback>()
    private val testedClass = MovieViewHolder(itemViewMock, itemCallbackMock)

    @Test
    fun `when the view holder binds a movie, then the item view is bind with a movie`() {
        testedClass.bind(movie)

        verify { itemViewMock.bind(movie) }
    }

    @Test
    fun `when the view holder binds a movie, then the item view is bind with a movie item callback`() {
        testedClass.bind(movie)

        verify { itemViewMock.bindCallback(movie, itemCallbackMock) }
    }

}
