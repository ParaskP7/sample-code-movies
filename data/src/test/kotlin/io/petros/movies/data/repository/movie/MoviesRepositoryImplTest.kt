package io.petros.movies.data.repository.movie

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.petros.movies.data.network.WebService
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {

    private lateinit var testedClass: MoviesRepositoryImpl
    private val webServiceMock = mock<WebService>()

    @Before
    fun setUp() {
        testedClass = MoviesRepositoryImpl(webServiceMock)
    }

    @Test
    fun `When load movies is triggered, then web service triggers load movies`() {
        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        verify(webServiceMock).loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
    }

}
