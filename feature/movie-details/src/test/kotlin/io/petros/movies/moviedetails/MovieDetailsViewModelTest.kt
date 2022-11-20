package io.petros.movies.moviedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.domain.interactor.movie.LoadMovieUseCase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.UnknownError
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.test.domain.movie
import io.petros.movies.test.utils.UnconfinedTestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
@Suppress("ThrowingExceptionsWithoutMessageOrCause")
class MovieDetailsViewModelTest {

    @get:Rule val dispatcherRule = UnconfinedTestDispatcherRule()

    @get:Rule val archRule = InstantTaskExecutorRule()

    private val movie = Result.Success(movie())
    private val movieStream: Flow<Result<Movie>> = flow { movie }
    private val errorStream: Flow<Result<Movie>> = flow { UnknownError(Exception()) }

    @Suppress("LateinitUsage")
    private lateinit var testedClass: MovieDetailsViewModel
    private val loadMovieUseCaseMock = mockk<LoadMovieUseCase>()
    private val state = mutableListOf<MovieDetailsState>()
    private val sideEffect = mutableListOf<MovieDetailsSideEffect>()

    @Before
    fun setUp() {
        testedClass = MovieDetailsViewModel(loadMovieUseCaseMock)
        testedClass.state().observeForever { state.add(it) }
        testedClass.sideEffect().observeForever { sideEffect.add(it) }
    }

    /* INIT */

    @Test
    fun `when initing movie, then the expected initing state is posted`() {
        expect {
            that(state.size).isEqualTo(1)
            that(sideEffect.size).isEqualTo(0)
            that(state.first()).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Init,
                    movie = Movie.Default,
                )
            )
        }
    }

    /* IDLE */

    @Test
    fun `when idling movie, then the expected idling state is posted`() {
        testedClass.process(MovieDetailsIntent.IdleMovies)

        expect {
            that(state.size).isEqualTo(2)
            that(sideEffect.size).isEqualTo(0)
            that(state.first()).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Init,
                    movie = Movie.Default,
                )
            )
            that(state.last()).isEqualTo(
                MovieDetailsState(
                    status = MovieDetailsStatus.Idle,
                    movie = Movie.Default,
                )
            )
        }
    }

    /* LOAD */

    @Test
    fun `when loading movie, then the expected loading state is posted`() = runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns flow { movieStream }

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        movieStream.collectLatest {
            expect {
                that(state.size).isEqualTo(1)
                that(sideEffect.size).isEqualTo(0)
                that(state.first()).isEqualTo(
                    MovieDetailsState(
                        status = MovieDetailsStatus.Loading,
                        movie = Movie.Default,
                    )
                )
            }
        }
    }

    @Test
    fun `when loading movie, then the load movie use case executes`() = runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns flow { movieStream }

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        movieStream.collectLatest {
            coVerify { loadMovieUseCaseMock(LoadMovieUseCase.Params(MOVIE_ID)) }
        }
    }

    @Test
    fun `when loading movie succeeds, then the expected loaded state is posted`() = runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns flow { movieStream }

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        movieStream.collectLatest {
            expect {
                that(state.size).isEqualTo(1)
                that(sideEffect.size).isEqualTo(0)
                that(state.first()).isEqualTo(
                    MovieDetailsState(
                        status = MovieDetailsStatus.Loaded,
                        movie = movie.value,
                    )
                )
            }
        }
    }

    @Test
    fun `when loading movie fails, then the expected loaded state is posted`() = runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns errorStream

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        errorStream.collectLatest {
            expect {
                that(state.size).isEqualTo(1)
                that(sideEffect.size).isEqualTo(0)
                that(state.first()).isEqualTo(
                    MovieDetailsState(
                        status = MovieDetailsStatus.Loaded,
                        movie = Movie.Default,
                    )
                )
            }
        }
    }

    @Test
    fun `when loading movie fails, then the expected error side effect is posted`() = runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns errorStream

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        errorStream.collectLatest {
            expect {
                that(state.size).isEqualTo(0)
                that(sideEffect.size).isEqualTo(1)
                that(sideEffect.first()).isEqualTo(
                    MovieDetailsReducer.once(
                        action = MovieDetailsAction.Error
                    )
                )
            }
        }
    }

    companion object {

        private const val MOVIE_ID = 419_704

    }

}
