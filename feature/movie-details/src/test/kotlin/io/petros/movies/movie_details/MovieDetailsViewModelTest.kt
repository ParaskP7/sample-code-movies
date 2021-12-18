package io.petros.movies.movie_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.domain.interactor.movie.LoadMovieUseCase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.UnknownError
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.test.domain.movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@Suppress("ThrowingExceptionsWithoutMessageOrCause")
class MovieDetailsViewModelTest {

    private val scope = TestScope()
    @get:Rule val rule = InstantTaskExecutorRule()

    private val movie = Result.Success(movie())
    private val movieStream: Flow<Result<Movie>> = flow { movie }
    private val errorStream: Flow<Result<Movie>> = flow { UnknownError(Exception()) }

    @Suppress("LateinitUsage") private lateinit var testedClass: MovieDetailsViewModel
    private val loadMovieUseCaseMock = mockk<LoadMovieUseCase>()
    private val stateMock = mockk<Observer<MovieDetailsState>>()
    private val sideEffectMock = mockk<Observer<MovieDetailsSideEffect>>()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher(scope.testScheduler))
        testedClass = MovieDetailsViewModel(loadMovieUseCaseMock)
        every { stateMock.onChanged(any()) } answers { }
        testedClass.state().observeForever(stateMock)
        every { sideEffectMock.onChanged(any()) } answers { }
        testedClass.sideEffect().observeForever(sideEffectMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /* INIT */

    @Test
    fun `when initing movie, then the expected initing state is posted`() {
        verify {
            stateMock.onChanged(
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

        verify {
            stateMock.onChanged(
                MovieDetailsState(
                    status = MovieDetailsStatus.Idle,
                    movie = Movie.Default,
                )
            )
        }
    }

    /* LOAD */

    @Test
    fun `when loading movie, then the expected loading state is posted`() = scope.runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns flow { movieStream }

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        movieStream.collectLatest {
            verify {
                stateMock.onChanged(
                    MovieDetailsState(
                        status = MovieDetailsStatus.Loading,
                        movie = Movie.Default,
                    )
                )
            }
        }
    }

    @Test
    fun `when loading movie, then the load movie use case executes`() = scope.runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns flow { movieStream }

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        movieStream.collectLatest {
            coVerify { loadMovieUseCaseMock(LoadMovieUseCase.Params(MOVIE_ID)) }
        }
    }

    @Test
    fun `when loading movie succeeds, then the expected loaded state is posted`() = scope.runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns flow { movieStream }

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        movieStream.collectLatest {
            verify {
                stateMock.onChanged(
                    MovieDetailsState(
                        status = MovieDetailsStatus.Loaded,
                        movie = movie.value,
                    )
                )
            }
        }
    }

    @Test
    fun `when loading movie fails, then the expected loaded state is posted`() = scope.runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns errorStream

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        errorStream.collectLatest {
            verify {
                stateMock.onChanged(
                    MovieDetailsState(
                        status = MovieDetailsStatus.Loaded,
                        movie = Movie.Default,
                    )
                )
            }
        }
    }

    @Test
    fun `when loading movie fails, then the expected error side effect is posted`() = scope.runTest {
        coEvery { loadMovieUseCaseMock(any()) } returns errorStream

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        errorStream.collectLatest {
            verify { sideEffectMock.onChanged(MovieDetailsReducer.once(MovieDetailsAction.Error)) }
        }
    }

    companion object {

        private const val MOVIE_ID = 419_704

    }

}
