package io.petros.movies.domain.interactor.movie

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMoviesResultPage
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class LoadMoviesUseCaseTest {

    private val params = LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

    private val moviesResultPage = provideMoviesResultPage()

    private lateinit var testedClass: LoadMoviesUseCase
    private val moviesRepositoryMock = mock<MoviesRepository>()

    @Before
    fun setUp() {
        testedClass = LoadMoviesUseCase(moviesRepositoryMock)
    }

    @Test
    fun `When load movies use case is build, then movies repository triggers load movies`() {
        runBlocking {
            whenever(moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE))
                .thenReturn(moviesResultPage)

            testedClass.execute(params)

            verify(moviesRepositoryMock).loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
        }
    }

    @Test
    fun `When load movies returns, then the movies result page is the expected one`() {
        runBlocking {
            whenever(moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE))
                .thenReturn(moviesResultPage)

            val result = testedClass.execute(params)

            expectThat(result).isEqualTo(moviesResultPage)
        }
    }

}
