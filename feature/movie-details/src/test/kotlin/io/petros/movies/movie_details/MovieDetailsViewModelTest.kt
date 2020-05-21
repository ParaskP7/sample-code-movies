package io.petros.movies.movie_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.domain.interactor.movie.LoadMovieUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.test.domain.movie
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    companion object {

        private const val MOVIE_ID = 419_704

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()
    @get:Rule val rule = InstantTaskExecutorRule()

    private val movie = Result.Success(movie())

    @Suppress("LateinitUsage") private lateinit var testedClass: MovieDetailsViewModel
    private val loadMovieUseCaseMock = mockk<LoadMovieUseCase>()
    private val stateMock = mockk<Observer<MovieDetailsState>>()
    private val sideEffectMock = mockk<Observer<MovieDetailsSideEffect>>()

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        testedClass = MovieDetailsViewModel(loadMovieUseCaseMock)
        every { stateMock.onChanged(any()) } answers { }
        testedClass.state().observeForever(stateMock)
        every { sideEffectMock.onChanged(any()) } answers { }
        testedClass.sideEffect().observeForever(sideEffectMock)
    }

    /* LOAD */

    @Test
    fun `when loading movie, then the expected loading state is posted`() {
        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        verify {
            stateMock.onChanged(
                MovieDetailsState(
                    status = MovieDetailsStatus.Loading,
                    movie = null
                )
            )
        }
    }

    @Test
    fun `when loading movie, then the load movie use case executes`() {
        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        coVerify { loadMovieUseCaseMock.execute(LoadMovieUseCase.Params(MOVIE_ID)) }
    }

    @Test
    fun `when loading movie succeeds, then the expected loaded state is posted`() {
        coEvery { loadMovieUseCaseMock.execute(any()) } returns movie

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        verify {
            stateMock.onChanged(
                MovieDetailsState(
                    status = MovieDetailsStatus.Loaded,
                    movie = movie.value
                )
            )
        }
    }

    @Test
    fun `when loading movie fails, then the expected loaded state is posted`() {
        coEvery { loadMovieUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        verify {
            stateMock.onChanged(
                MovieDetailsState(
                    status = MovieDetailsStatus.Loaded,
                    movie = null
                )
            )
        }
    }

    @Test
    fun `when loading movie fails, then the expected error side effect is posted`() {
        coEvery { loadMovieUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))

        verify { sideEffectMock.onChanged(MovieDetailsReducer.once(MovieDetailsAction.Error)) }
    }

}
