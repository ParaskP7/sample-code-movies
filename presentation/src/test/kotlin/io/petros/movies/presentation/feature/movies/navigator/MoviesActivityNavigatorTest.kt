package io.petros.movies.presentation.feature.movies.navigator

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsLauncher
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie
import io.petros.movies.presentation.feature.navigator.Launcher
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.junit.Before
import org.junit.Test

class MoviesActivityNavigatorTest {

    private val movie = provideMovie()

    private lateinit var testedClass: MoviesActivityNavigator
    private val movieDetailsLauncherMock = mock<MovieDetailsLauncher>()
    private val launcherMock = mock<Launcher>()

    @Before
    fun setUp() {
        testedClass = MoviesActivityNavigator(movieDetailsLauncherMock)
        testedClass.launcher = launcherMock
    }

    @Test
    fun `When navigating from movies activity, then movie details activity launches`() {
        val sharedElementMovie = SharedElementMovie(movie, mock())

        testedClass.navigate(sharedElementMovie)

        verify(movieDetailsLauncherMock).launch(sharedElementMovie)
    }

}
