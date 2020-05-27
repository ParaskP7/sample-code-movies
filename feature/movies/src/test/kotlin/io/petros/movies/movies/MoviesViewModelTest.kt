package io.petros.movies.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()
    @get:Rule val rule = InstantTaskExecutorRule()

    private val moviesPage = Result.Success(moviesPage())

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

    /* IDLE */

    @Test
    fun `when idling movies, then the expected idling state is posted`() {
        testedClass.process(MoviesIntent.IdleMovies)

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Idle,
                    movies = PaginationData()
                )
            )
        }
    }

    /* LOAD */

    @Test
    fun `when loading movies, then the expected loading state is posted`() {
        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loading,
                    movies = PaginationData()
                )
            )
        }
    }

    @Test
    fun `when loading movies, then the load movies use case executes`() {
        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)) }
    }

    @Test
    fun `when loading movies succeeds, then the expected loaded state is posted`() {
        val paginationData = PaginationData<Movie>()
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage

        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loaded,
                    movies = PaginationData(
                        paginationData.allPageItems + moviesPage.value.items,
                        moviesPage.value,
                        moviesPage.value.nextPage
                    )
                )
            )
        }
    }

    @Test
    fun `when loading movies fails, then the expected loaded state is posted`() {
        val paginationData = PaginationData<Movie>()
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loaded,
                    movies = PaginationData(
                        paginationData.allPageItems,
                        MoviesPage(paginationData.nextPage, emptyList()),
                        paginationData.nextPage
                    )
                )
            )
        }
    }

    @Test
    fun `when loading movies fails, then the expected error side effect is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))

        verify { sideEffectMock.onChanged(MoviesReducer.once(MoviesAction.Error)) }
    }

    /* RELOAD */

    @Test
    fun `when reloading movies, then the expected reload state is posted`() {
        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = null,
                    month = null,
                    status = MoviesStatus.Init,
                    movies = PaginationData()
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
                    status = MoviesStatus.Loading,
                    movies = PaginationData()
                )
            )
        }
    }

    @Test
    fun `when reloading movies, then the load movies use case executes`() {
        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
    }

    @Test
    fun `when reloading movies succeeds, then the expected loaded state is posted`() {
        val paginationData = PaginationData<Movie>()
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage

        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loaded,
                    movies = PaginationData(
                        paginationData.allPageItems + moviesPage.value.items,
                        moviesPage.value,
                        moviesPage.value.nextPage
                    )
                )
            )
        }
    }

    @Test
    fun `when reloading movies fails, then the expected loaded state is posted`() {
        val paginationData = PaginationData<Movie>()
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify {
            stateMock.onChanged(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    status = MoviesStatus.Loaded,
                    movies = PaginationData(
                        paginationData.allPageItems,
                        MoviesPage(paginationData.nextPage, emptyList()),
                        paginationData.nextPage
                    )
                )
            )
        }
    }

    @Test
    fun `when reloading movies fails, then the expected error side effect is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        verify { sideEffectMock.onChanged(MoviesReducer.once(MoviesAction.Error)) }
    }

}
