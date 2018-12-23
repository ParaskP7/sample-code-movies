package io.petros.movies.presentation.feature.movies.list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.view.MovieItemView
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieViewHolderRobolectricTest {

    private val movie = provideMovie()

    private lateinit var testedClass: MovieViewHolder
    private val itemViewMock = mock<MovieItemView>()
    private val callbackMock = mock<MovieCallback>()

    @Before
    fun setUp() {
        testedClass = MovieViewHolder(itemViewMock, callbackMock)
    }

    @Test
    fun `When the view holder binds a movie, then the item view is bind with a movie`() {
        testedClass.bind(movie)

        verify(itemViewMock).bind(movie)
    }

    @Test
    fun `When the view holder binds a movie, then the item view is bind with a movie callback`() {
        testedClass.bind(movie)

        verify(itemViewMock).bindCallback(movie, callbackMock)
    }

}
