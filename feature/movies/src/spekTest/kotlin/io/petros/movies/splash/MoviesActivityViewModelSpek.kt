package io.petros.movies.splash

import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.utils.ViewModelSpek
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movies.MoviesActivityViewModel
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.movie
import io.petros.movies.test.domain.moviesResultPage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

private val statusObservableMock = mockk<Observer<AdapterStatus>>()
private val moviesResultPageObservableMock = mockk<Observer<PaginationData<Movie>>>()

@ExperimentalCoroutinesApi
private fun setupViewModel(testedClass: MoviesActivityViewModel) {
    testedClass.statusObservable.observeForever(statusObservableMock)
    testedClass.moviesObservable.observeForever(moviesResultPageObservableMock)
}

@ExperimentalCoroutinesApi
class MoviesActivityViewModelSpek : ViewModelSpek({

    val previousMoviesResultPage = moviesResultPage(NEXT_PAGE, listOf(movie(), movie()))
    val moviesResultPage = moviesResultPage()
    val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()

    Feature("Movies activity view model") {
        val testedClass by memoized { MoviesActivityViewModel(loadMoviesUseCaseMock) }
        Scenario("on success") {
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage
            }
            When("loading movies") {
                testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
            }
            Then("a loading status is posted") {
                coVerify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)) }
            }
            Then("an idle status is posted") {
                verify { statusObservableMock.onChanged(AdapterStatus.IDLE) }
            }
            Then("a page is posted") {
                verify { moviesResultPageObservableMock.onChanged(testedClass.paginationData.addPage(moviesResultPage)) }
            }
        }
        Scenario("on failure") {
            Given("an exception as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } answers { throw LoadMoviesUseCase.Error(Exception()) }
            }
            When("loading movies") {
                testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
            }
            Then("a loading status is posted") {
                coVerify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)) }
            }
            Then("an error status is posted") {
                coVerify { statusObservableMock.onChanged(AdapterStatus.ERROR) }
            }
            Then("a null page is posted") {
                coVerify { moviesResultPageObservableMock.onChanged(null) }
            }
        }
    }

    Feature("Movies activity view model with no pages") {
        val testedClass by memoized { MoviesActivityViewModel(loadMoviesUseCaseMock) }
        Scenario("loading or restoring movies") {
            Given("no pages") {
                setupViewModel(testedClass)
            }
            When("loading movies or restoring") {
                testedClass.loadMoviesOrRestore()
            }
            Then("a loading status is posted") {
                coVerify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(null, null, null)) }
            }
        }
    }

    Feature("Movies activity view model with a page") {
        val testedClass by memoized { MoviesActivityViewModel(loadMoviesUseCaseMock) }
        Scenario("loading or restoring movies") {
            Given("a pages") {
                setupViewModel(testedClass)
                testedClass.paginationData.addPage(previousMoviesResultPage)
            }
            When("loading movies or restoring") {
                testedClass.loadMoviesOrRestore()
            }
            Then("restore is triggered") {
                verify { moviesResultPageObservableMock.onChanged(testedClass.paginationData) }
            }
        }
    }

    Feature("Movies activity view model during reload") {
        val testedClass by memoized { MoviesActivityViewModel(loadMoviesUseCaseMock) }
        Scenario("reloading movies") {
            Given("a page") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage
                testedClass.paginationData.addPage(previousMoviesResultPage)
            }
            When("reloading movies") {
                testedClass.reloadMovies(MOVIE_YEAR, MOVIE_MONTH)
            }
            Then("the existing data gets cleared") {
                expect { that(testedClass.paginationData.items().size).isEqualTo(moviesResultPage.movies.size) }
            }
            Then("a new load of movies is triggered") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
            }
        }
    }

})
