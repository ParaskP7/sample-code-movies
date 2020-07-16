package io.petros.movies.domain.interactor.movie

import androidx.paging.PagingData
import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadMoviesUseCaseTest {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val params = LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)

    private val moviesPage = Result.Success(moviesPage())
    private val moviesPageStream = mockk<Flow<PagingData<Movie>>>()

    private val moviesRepositoryMock = mockk<MoviesRepository>()
    private val testedClass = LoadMoviesUseCase(moviesRepositoryMock)

    @Test
    fun `when executing the use case, then the movies page is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns moviesPage

        val result = testedClass.execute(params)

        expect { that(result).isEqualTo(moviesPage) }
    }

    @Test
    fun `when invoking the use case, then the movies page stream is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { moviesRepositoryMock.loadMoviesStream(MOVIE_YEAR, MOVIE_MONTH) } returns moviesPageStream

        val result = testedClass(params)

        expect { that(result).isEqualTo(moviesPageStream) }
    }

}
