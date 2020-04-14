package io.petros.movies.movies

import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.utils.ViewModelSpek
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.test.domain.movie
import io.petros.movies.test.domain.moviesPage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

private val statusObservableMock = mockk<Observer<AdapterStatus>>()
private val moviesPageObservableMock = mockk<Observer<PaginationData<Movie>>>()

@ExperimentalCoroutinesApi
private fun setupViewModel(testedClass: MoviesViewModel) {
    testedClass.statusObservable.observeForever(statusObservableMock)
    testedClass.moviesObservable.observeForever(moviesPageObservableMock)
}

@ExperimentalCoroutinesApi
class MoviesViewModelSpek : ViewModelSpek({

    val previousMoviesPage = moviesPage(SECOND_PAGE, listOf(movie(), movie()))
    val moviesPage = Result.Success(moviesPage())
    val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()

    Feature("Movies view model") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
        Scenario("on success") {
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage
            }
            When("loading movies") {
                testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)
            }
            Then("a loading status is posted") {
                coVerify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)) }
            }
            Then("an idle status is posted") {
                verify { statusObservableMock.onChanged(AdapterStatus.IDLE) }
            }
            Then("a page is posted") {
                verify { moviesPageObservableMock.onChanged(testedClass.paginationData.addPage(moviesPage.value)) }
            }
        }
        Scenario("on failure") {
            Given("an exception as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())
            }
            When("loading movies") {
                testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)
            }
            Then("a loading status is posted") {
                coVerify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)) }
            }
            Then("an error status is posted") {
                coVerify { statusObservableMock.onChanged(AdapterStatus.ERROR) }
            }
            Then("a null page is posted") {
                coVerify { moviesPageObservableMock.onChanged(null) }
            }
        }
    }

    Feature("Movies view model with no pages") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
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

    Feature("Movies view model with a page") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
        Scenario("loading or restoring movies") {
            Given("a pages") {
                setupViewModel(testedClass)
                testedClass.paginationData.addPage(previousMoviesPage)
            }
            When("loading movies or restoring") {
                testedClass.loadMoviesOrRestore()
            }
            Then("restore is triggered") {
                verify { moviesPageObservableMock.onChanged(testedClass.paginationData) }
            }
        }
    }

    Feature("Movies view model during reload") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
        Scenario("reloading movies") {
            Given("a page") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage
                testedClass.paginationData.addPage(previousMoviesPage)
            }
            When("reloading movies") {
                testedClass.reloadMovies(MOVIE_YEAR, MOVIE_MONTH)
            }
            Then("the existing data gets cleared") {
                expect { that(testedClass.paginationData.items().size).isEqualTo(moviesPage.value.movies.size) }
            }
            Then("a new load of movies is triggered") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
            }
        }
    }

}) {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
