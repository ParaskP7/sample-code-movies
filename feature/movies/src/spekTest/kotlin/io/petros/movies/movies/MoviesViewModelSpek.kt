package io.petros.movies.movies

import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.utils.ViewModelSpek
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.moviesPage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.spekframework.spek2.style.gherkin.Feature

private val stateMock = mockk<Observer<MoviesState>>()
private val sideEffectMock = mockk<Observer<MoviesSideEffect>>()

@ExperimentalCoroutinesApi
private fun setupViewModel(testedClass: MoviesViewModel) {
    every { stateMock.onChanged(any()) } answers { }
    testedClass.state().observeForever(stateMock)
    every { sideEffectMock.onChanged(any()) } answers { }
    testedClass.sideEffect().observeForever(sideEffectMock)
}

@ExperimentalCoroutinesApi
class MoviesViewModelSpek : ViewModelSpek({

    val moviesPage = Result.Success(moviesPage())
    val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()

    Feature("Movies view model for init") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
        Scenario("initing") {
            When("initing movies") {
                setupViewModel(testedClass)
            }
            Then("the expected initing state is posted") {
                coVerify {
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
        }
    }

    Feature("Movies view model for idle") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
        Scenario("idling") {
            When("idling movies") {
                setupViewModel(testedClass)
                testedClass.process(MoviesIntent.IdleMovies(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the expected idling state is posted") {
                coVerify {
                    stateMock.onChanged(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            status = MoviesStatus.Idle,
                            movies = PaginationData()
                        )
                    )
                }
            }
        }
    }

    Feature("Movies view model for load") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
        Scenario("loading") {
            When("loading movies") {
                setupViewModel(testedClass)
                testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))
            }
            Then("the expected loading state is posted") {
                coVerify {
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
        }
        Scenario("on success") {
            val paginationData = PaginationData<Movie>()
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage
            }
            When("loading movies") {
                testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)) }
            }
            Then("the expected loaded state is posted") {
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
        }
        Scenario("on failure") {
            val paginationData = PaginationData<Movie>()
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())
            }
            When("loading movies") {
                testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE))
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)) }
            }
            Then("the expected loaded state is posted") {
                verify {
                    stateMock.onChanged(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            status = MoviesStatus.Loaded,
                            movies = PaginationData(
                                paginationData.allPageItems + emptyList(),
                                MoviesPage(paginationData.nextPage, emptyList()),
                                paginationData.nextPage
                            )
                        )
                    )
                }
            }
            Then("the expected error side effect is posted") {
                verify { sideEffectMock.onChanged(MoviesReducer.once(MoviesAction.Error)) }
            }
        }
    }

    Feature("Movies view model for reload") {
        val testedClass by memoized { MoviesViewModel(loadMoviesUseCaseMock) }
        Scenario("reloading") {
            When("reloading movies") {
                setupViewModel(testedClass)
                testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the expected reloading state is posted") {
                coVerify {
                    stateMock.onChanged(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            status = MoviesStatus.Init,
                            movies = PaginationData()
                        )
                    )
                }
            }
            Then("the expected loading state is posted") {
                coVerify {
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
        }
        Scenario("on success") {
            val paginationData = PaginationData<Movie>()
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage
            }
            When("reloading movies") {
                testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
            }
            Then("the expected loaded state is posted") {
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
        }
        Scenario("on failure") {
            val paginationData = PaginationData<Movie>()
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())
            }
            When("reloading movies") {
                testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
            }
            Then("the expected loaded state is posted") {
                verify {
                    stateMock.onChanged(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            status = MoviesStatus.Loaded,
                            movies = PaginationData(
                                paginationData.allPageItems + emptyList(),
                                MoviesPage(paginationData.nextPage, emptyList()),
                                paginationData.nextPage
                            )
                        )
                    )
                }
            }
            Then("the expected error side effect is posted") {
                verify { sideEffectMock.onChanged(MoviesReducer.once(MoviesAction.Error)) }
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
