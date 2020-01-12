package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.provideMoviesResultPage
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadMoviesUseCaseTest {

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val params = LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

    private val moviesResultPage = provideMoviesResultPage()

    private lateinit var testedClass: LoadMoviesUseCase
    private val moviesRepositoryMock = mockk<MoviesRepository>()

    @Before
    fun setUp() {
        testedClass = LoadMoviesUseCase(moviesRepositoryMock)
    }

    @Test
    fun `when executing the use case, then the repository triggers load movies`() = runBlocking {
        coEvery { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) } returns moviesResultPage

        testedClass.execute(params)

        coVerify { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
    }

    @Test
    fun `when executing the use case, then the movies result page is the expected one`() = runBlocking {
        coEvery { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) } returns moviesResultPage

        val result = testedClass.execute(params)

        expect { that(result).isEqualTo(moviesResultPage) }
    }

}
