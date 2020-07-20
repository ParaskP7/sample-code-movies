package io.petros.movies.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.LoadType
import androidx.paging.PagingData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()
    @get:Rule val rule = InstantTaskExecutorRule()

    private val moviesPage = mockk<Flow<PagingData<Movie>>>()

    @Suppress("LateinitUsage") private lateinit var testedClass: MoviesViewModel
    private val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()
    private val stateMock = mockk<Observer<MoviesState>>()
    private val sideEffectMock = mockk<Observer<MoviesSideEffect>>()

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        testedClass = MoviesViewModel(loadMoviesUseCaseMock)
        every { stateMock.onChanged(any()) } answers { }
        testedClass.state().observeForever(stateMock)
        every { sideEffectMock.onChanged(any()) } answers { }
        testedClass.sideEffect().observeForever(sideEffectMock)
    }

    /* INIT */

    @Test
    fun `when initing movies, then the expected initing state is posted`() {
        verify {
            stateMock.onChanged(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    /* LOAD */

    @Test
    fun `when loading movies, then the load movies use case executes`() {
        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))

        coVerify { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
    }

    @Test
    @Ignore("Figure out a way to test this scenario.")
    fun `when loading movies succeeds, then the expected loaded state is posted`() {
        coEvery { loadMoviesUseCaseMock(any()) } returns moviesPage

        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = eq(any()),
                )
            )
        }
    }

    @Test
    fun `when loading movies fails, then the expected loaded state is posted`() {
        val error = NetworkError(Exception())

        testedClass.process(MoviesIntent.ErrorMovies(error.cause, LoadType.APPEND))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `when loading movies fails, then the expected error side effect is posted`() {
        val error = NetworkError(Exception())

        testedClass.process(MoviesIntent.ErrorMovies(error.cause, LoadType.APPEND))

        verify { sideEffectMock.onChanged(MoviesReducer.once(MoviesAction.Error(LoadType.APPEND))) }
    }

    /* RELOAD */

    @Test
    fun `when reloading movies, then the expected reload state is posted`() {
        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `when reloading movies, then the expected loading state is posted`() {
        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `when reloading movies, then the load movies use case executes`() {
        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        coVerify { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
    }

    @Test
    @Ignore("Figure out a way to test this scenario.")
    fun `when reloading movies succeeds, then the expected loaded state is posted`() {
        coEvery { loadMoviesUseCaseMock(any()) } returns moviesPage

        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = eq(any()),
                )
            )
        }
    }

}
