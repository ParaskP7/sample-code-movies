package io.petros.movies.movies.navigator

import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.movie_details.navigator.MovieDetailsLauncher
import io.petros.movies.test.domain.movie
import org.junit.Test

class MoviesNavigatorImplTest {

    private val movieDetailsLauncherMock = mockk<MovieDetailsLauncher>()
    private val testedClass = MoviesNavigatorImpl(movieDetailsLauncherMock)

    @Test
    fun `when navigating from movies activity, then movie details activity launches with it`() {
        val movie = movie()

        testedClass.navigate(movie)

        verify { movieDetailsLauncherMock.launch(movie) }
    }

}
