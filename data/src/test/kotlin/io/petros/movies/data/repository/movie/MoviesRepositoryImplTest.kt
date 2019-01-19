package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.data.network.WebService
import io.petros.movies.test.domain.TestMoviesProvider
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {

    private val moviesResultPage = TestMoviesProvider.provideMoviesResultPage()

    private lateinit var testedClass: MoviesRepositoryImpl
    private val webServiceMock = mockk<WebService>()

    @Before
    fun setUp() {
        testedClass = MoviesRepositoryImpl(webServiceMock)
    }

    @Test
    fun `When load movies is triggered, then web service triggers load movies`() = runBlocking {
        coEvery { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) } returns moviesResultPage

        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        coVerify { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
    }

}
