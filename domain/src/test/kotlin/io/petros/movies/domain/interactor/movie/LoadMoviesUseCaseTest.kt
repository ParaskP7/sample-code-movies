package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadMoviesUseCaseTest {

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val params = LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

    private val moviesPage = Result.Success(moviesPage())

    private val moviesRepositoryMock = mockk<MoviesRepository>()
    private val testedClass = LoadMoviesUseCase(moviesRepositoryMock)

    @Test
    fun `when executing the use case, then the repository triggers load movies`() = coroutineScope.runBlockingTest {
        coEvery { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) } returns moviesPage

        testedClass.execute(params)

        coVerify { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
    }

    @Test
    fun `when executing the use case, then the movies page is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) } returns moviesPage

        val result = testedClass.execute(params)

        expect { that(result).isEqualTo(moviesPage) }
    }

}
