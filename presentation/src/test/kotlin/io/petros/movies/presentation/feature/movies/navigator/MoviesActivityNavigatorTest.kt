package io.petros.movies.presentation.feature.movies.navigator

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsLauncher
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.junit.Before
import org.junit.Test

class MoviesActivityNavigatorTest {

    private val movie = provideMovie()

    private lateinit var testedClass: MoviesActivityNavigator
    private val movieDetailsLauncherMock = mock<MovieDetailsLauncher>()

    @Before
    fun setUp() {
        testedClass = MoviesActivityNavigator(movieDetailsLauncherMock)
    }

    @Test
    fun `When navigating from movies activity, then movie details activity launches`() {
        val sharedElementMovie = SharedElementMovie(movie, mock())

        testedClass.navigate(sharedElementMovie)

        verify(movieDetailsLauncherMock).launch(sharedElementMovie)
    }

}
