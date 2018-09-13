package io.petros.movies.data.repository.movie

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.data.network.WebService
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
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
        testedClass.loadMovies(MOVIE_YEAR)

        verify(webServiceMock).loadMovies(MOVIE_YEAR)
    }

}
