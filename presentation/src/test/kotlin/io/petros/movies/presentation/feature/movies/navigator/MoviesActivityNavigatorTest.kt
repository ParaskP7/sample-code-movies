package io.petros.movies.presentation.feature.movies.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsLauncher
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie
import io.petros.movies.test.domain.provideMovie
import org.junit.Before
import org.junit.Test

class MoviesActivityNavigatorTest {

    private val movie = provideMovie()

    private lateinit var testedClass: MoviesActivityNavigator
    private val movieDetailsLauncherMock = mockk<MovieDetailsLauncher>()

    @Before
    fun setUp() {
        testedClass = MoviesActivityNavigator(movieDetailsLauncherMock)
    }

    @Test
    fun `given shared element movie, when navigating from movies activity, then movie details activity launches with it`() {
        val sharedElementMovie = SharedElementMovie(movie, mockk())

        testedClass.navigate(sharedElementMovie)

        verify { movieDetailsLauncherMock.launch(sharedElementMovie) }
    }

}
