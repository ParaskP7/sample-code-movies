package io.petros.movies.movie_details

import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.utils.ViewModelSpek
import io.petros.movies.domain.interactor.movie.LoadMovieUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.test.domain.movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.style.gherkin.Feature

private val stateMock = mockk<Observer<MovieDetailsState>>()
private val sideEffectMock = mockk<Observer<MovieDetailsSideEffect>>()

@ExperimentalCoroutinesApi
private fun setupViewModel(testedClass: MovieDetailsViewModel) {
    every { stateMock.onChanged(any()) } answers { }
    testedClass.state().observeForever(stateMock)
    every { sideEffectMock.onChanged(any()) } answers { }
    testedClass.sideEffect().observeForever(sideEffectMock)
}

@ExperimentalCoroutinesApi
class MovieDetailsViewModelSpek : ViewModelSpek({

    val movie = Result.Success(movie())
    val loadMovieUseCaseMock = mockk<LoadMovieUseCase>()

    Feature("Movies view model for load") {
        val testedClass by memoized { MovieDetailsViewModel(loadMovieUseCaseMock) }
        Scenario("loading") {
            When("loading movies") {
                setupViewModel(testedClass)
                testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))
            }
            Then("the expected loading state is posted") {
                coVerify {
                    stateMock.onChanged(
                        MovieDetailsState(
                            status = MovieDetailsStatus.Loading,
                            movie = null
                        )
                    )
                }
            }
        }
        Scenario("on success") {
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMovieUseCaseMock.execute(any()) } returns movie
            }
            When("loading movies") {
                testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))
            }
            Then("the load movies use case executes") {
                coVerify { loadMovieUseCaseMock.execute(LoadMovieUseCase.Params(MOVIE_ID)) }
            }
            Then("the expected loaded state is posted") {
                verify {
                    stateMock.onChanged(
                        MovieDetailsState(
                            status = MovieDetailsStatus.Loaded,
                            movie = movie.value
                        )
                    )
                }
            }
        }
        Scenario("on failure") {
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMovieUseCaseMock.execute(any()) } returns NetworkError(Exception())
            }
            When("loading movies") {
                testedClass.process(MovieDetailsIntent.LoadMovie(MOVIE_ID))
            }
            Then("the load movies use case executes") {
                coVerify { loadMovieUseCaseMock.execute(LoadMovieUseCase.Params(MOVIE_ID)) }
            }
            Then("the expected loaded state is posted") {
                verify {
                    stateMock.onChanged(
                        MovieDetailsState(
                            status = MovieDetailsStatus.Loaded,
                            movie = null
                        )
                    )
                }
            }
            Then("the expected error side effect is posted") {
                verify { sideEffectMock.onChanged(MovieDetailsReducer.once(MovieDetailsAction.Error)) }
            }
        }
    }

}) {

    companion object {

        private const val MOVIE_ID = 419_704

    }

}
