package io.petros.movies.movies

import androidx.lifecycle.Observer
import androidx.paging.LoadType
import androidx.paging.PagingData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.utils.ViewModelSpek
import io.petros.movies.domain.interactor.movie.LoadDateUseCase
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
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

private const val MOVIE_YEAR = 2018
private const val MOVIE_MONTH = 7

private val date = Result.Success(Pair(MOVIE_YEAR, MOVIE_MONTH))
private val moviesPage = mockk<PagingData<Movie>>()
private val moviesPageStream = flow<PagingData<Movie>> { moviesPage }

@Suppress("ForbiddenComment")
@ExperimentalCoroutinesApi
class MoviesViewModelSpek : ViewModelSpek({

    val loadDateUseCaseMock = mockk<LoadDateUseCase>()
    val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()

    Feature("Movies view model for init") {
        val testedClass by memoized { MoviesViewModel(loadDateUseCaseMock, loadMoviesUseCaseMock) }
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
                            movies = PagingData.empty(),
                        )
                    )
                }
            }
        }
    }

    Feature("Movies view model for date load") {
        val testedClass by memoized { MoviesViewModel(loadDateUseCaseMock, loadMoviesUseCaseMock) }
        Scenario("on success") {
            Given("a date as a result") {
                setupViewModel(testedClass)
                coEvery { loadDateUseCaseMock() } returns date
            }
            When("loading date") {
                testedClass.process(MoviesIntent.LoadDate)
            }
            Then("the load date use case executes") {
                coVerify { loadDateUseCaseMock() }
            }
            Then("the expected loaded state is posted") {
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
        }
        Scenario("on failure") {
            Given("a date as a result") {
                setupViewModel(testedClass)
                coEvery { loadDateUseCaseMock() } returns NetworkError(Exception())
            }
            When("loading date") {
                testedClass.process(MoviesIntent.LoadDate)
            }
            Then("the load date use case executes") {
                coVerify { loadDateUseCaseMock() }
            }
            Then("the expected loaded state is posted") {
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
            Then("the expected error side effect is posted") {
                verify { sideEffectMock.onChanged(MoviesReducer.once(MoviesAction.DateError)) }
            }
        }
    }

    Feature("Movies view model for movies load") {
        val testedClass by memoized { MoviesViewModel(loadDateUseCaseMock, loadMoviesUseCaseMock) }
        Scenario("on success") {
            Given("initial load") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock(any()) } returns moviesPageStream
            }
            When("loading movies") {
                testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH)) }
            }
            Then("the expected loaded state is posted") {
                runBlocking {
                    moviesPageStream.collectLatest {
                        verify {
                            stateMock.onChanged(
                                MoviesState(
                                    year = MOVIE_YEAR,
                                    month = MOVIE_MONTH,
                                    movies = moviesPage,
                                )
                            )
                        }
                    }
                }
            }
        }
        Scenario("on failure") {
            val error = NetworkError(Exception())
            Given("initial load") {
                setupViewModel(testedClass)
            }
            When("loading movies") {
                testedClass.process(MoviesIntent.ErrorMovies(error.cause, LoadType.APPEND))
            }
            Then("the expected loaded state is posted") {
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
            Then("the expected error side effect is posted") {
                verify { sideEffectMock.onChanged(MoviesReducer.once(MoviesAction.MoviesError(LoadType.APPEND))) }
            }
        }
        // TODO: Figure out a way to test this scenario.
        /*Scenario("idling") {
            Given("subsequent load") {
                setupViewModel(testedClass)
            }
            When("loading movies") {
                testedClass.process(MoviesIntent.LoadMovies(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the load movies use case does not execute") {
                coVerify { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
            }
            Then("the expected idle state is posted") {
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
        }*/
    }

    Feature("Movies view model for movies reload") {
        val testedClass by memoized { MoviesViewModel(loadDateUseCaseMock, loadMoviesUseCaseMock) }
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
                            movies = PagingData.empty(),
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
                            movies = PagingData.empty(),
                        )
                    )
                }
            }
        }
        Scenario("on success") {
            Given("a page as a result") {
                setupViewModel(testedClass)
                coEvery { loadMoviesUseCaseMock(any()) } returns moviesPageStream
            }
            When("reloading movies") {
                testedClass.process(MoviesIntent.ReloadMovies(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the load movies use case executes") {
                coVerify { loadMoviesUseCaseMock(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH)) }
            }
            Then("the expected loaded state is posted") {
                runBlocking {
                    moviesPageStream.collectLatest {
                        verify {
                            stateMock.onChanged(
                                MoviesState(
                                    year = MOVIE_YEAR,
                                    month = MOVIE_MONTH,
                                    movies = moviesPage,
                                )
                            )
                        }
                    }
                }
            }
        }
    }

})
