package io.petros.movies.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.LoadType
import androidx.paging.PagingData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.domain.interactor.movie.LoadDateUseCase
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.test.utils.UnconfinedTestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
@Suppress("ThrowingExceptionsWithoutMessageOrCause")
class MoviesViewModelTest {

    @get:Rule val dispatcherRule = UnconfinedTestDispatcherRule()
    @get:Rule val archRule = InstantTaskExecutorRule()

    private val date = Result.Success(Pair(MOVIE_YEAR, MOVIE_MONTH))
    private val moviesPage = mockk<PagingData<Movie>>()
    private val moviesPageStream = flow<PagingData<Movie>> { moviesPage }

    @Suppress("LateinitUsage")
    private lateinit var testedClass: MoviesViewModel
    private val loadDateUseCaseMock = mockk<LoadDateUseCase>()
    private val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()
    private val state = mutableListOf<MoviesState>()
    private val sideEffect = mutableListOf<MoviesSideEffect>()

    @Before
    fun setUp() {
        testedClass = MoviesViewModel(loadDateUseCaseMock, loadMoviesUseCaseMock)
        testedClass.state().observeForever { state.add(it) }
        testedClass.sideEffect().observeForever { sideEffect.add(it) }
    }

    /* INIT */

    @Test
    fun `when initing movies, then the expected initing state is posted`() {
        expect {
            that(state.size).isEqualTo(1)
            that(sideEffect.size).isEqualTo(0)
            that(state.first()).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    /* DATE - LOAD */

    @Test
    fun `when loading date, then the load date use case executes`() = runTest {
        coEvery { loadDateUseCaseMock() } returns date

        testedClass.process(MoviesIntent.LoadDate)

        coVerify { loadDateUseCaseMock.invoke() }
    }

    @Test
    fun `when loading date succeeds, then the expected loaded state is posted`() = runTest {
        coEvery { loadDateUseCaseMock() } returns date

        testedClass.process(MoviesIntent.LoadDate)

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(0)
            that(state.first()).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
            that(state.last()).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `when loading date fails, then the expected loaded state is posted`() = runTest {
        coEvery { loadDateUseCaseMock() } returns NetworkError(Exception())

        testedClass.process(MoviesIntent.LoadDate)

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(1)
            that(state.first()).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
            that(state.last()).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `when loading date fails, then the expected error side effect is posted`() = runTest {
        coEvery { loadDateUseCaseMock() } returns NetworkError(Exception())

        testedClass.process(MoviesIntent.LoadDate)

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(1)
            that(sideEffect.first()).isEqualTo(
                MoviesReducer.once(
                    action = MoviesAction.DateError
                )
            )
        }
    }

    /* MOVIES - LOAD */

    @Test
    fun `given initial load, when loading movies, then the load movies use case executes`() = runTest {
        coEvery { loadMoviesUseCaseMock(any()) } returns moviesPageStream

        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))

        moviesPageStream.collectLatest {
            coVerify { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH)) }
        }
    }

    @Test
    fun `when loading movies succeeds, then the expected loaded state is posted`() = runTest {
        coEvery { loadMoviesUseCaseMock(any()) } returns moviesPageStream

        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))

        moviesPageStream.collectLatest {
            expect {
                that(state.size).isEqualTo(1)
                that(sideEffect.size).isEqualTo(0)
                that(state.first()).isEqualTo(
                    MoviesState(
                        year = MOVIE_YEAR,
                        month = MOVIE_MONTH,
                        movies = moviesPage,
                    )
                )
            }
        }
    }

    @Test
    fun `when loading movies fails, then the expected loaded state is posted`() {
        val error = NetworkError(Exception())

        testedClass.process(MoviesIntent.ErrorMovies(error.cause, LoadType.APPEND))

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(1)
            that(state.first()).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
            that(state.last()).isEqualTo(
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

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(1)
            that(sideEffect.first()).isEqualTo(
                MoviesReducer.once(
                    action = MoviesAction.MoviesError(LoadType.APPEND)
                )
            )
        }
    }

    @Test
    @Ignore("Figure out a way to test this scenario.")
    fun `given subsequent load, when loading movies, then the load movies use case does not execute`() {
        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))

        coVerify(exactly = 0) { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH)) }
    }

    @Test
    @Ignore("Figure out a way to test this scenario.")
    fun `given subsequent load, when loading movies, then the expected idle state is posted`() {
        testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))

        expect {
            that(state.size).isEqualTo(1)
            that(sideEffect.size).isEqualTo(0)
            that(state.first()).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    /* MOVIES - RELOAD */

    @Test
    fun `when reloading movies, then the expected reload state is posted`() {
        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(0)
            that(state.first()).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
            that(state.last()).isEqualTo(
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

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(0)
            that(state.first()).isEqualTo(
                MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            )
            that(state.last()).isEqualTo(
                MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = PagingData.empty(),
                )
            )
        }
    }

    @Test
    fun `when reloading movies, then the load movies use case executes`() = runTest {
        coEvery { loadMoviesUseCaseMock(any()) } returns moviesPageStream

        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        moviesPageStream.collectLatest {
            coVerify { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH)) }
        }
    }

    @Test
    fun `when reloading movies succeeds, then the expected loaded state is posted`() = runTest {
        coEvery { loadMoviesUseCaseMock(any()) } returns moviesPageStream

        testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))

        moviesPageStream.collectLatest {
            expect {
                that(state.size).isEqualTo(1)
                that(sideEffect.size).isEqualTo(0)
                that(state.first()).isEqualTo(
                    MoviesState(
                        year = MOVIE_YEAR,
                        month = MOVIE_MONTH,
                        movies = moviesPage,
                    )
                )
            }
        }
    }

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
