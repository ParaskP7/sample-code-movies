package io.petros.movies.movies

import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.domain.model.movie.MoviesStatus
import io.petros.movies.test.domain.movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class MoviesStateSpek : Spek({

    val firstPageItems = listOf(movie(id = 1), movie(id = 2), movie(id = 3))

    Feature("Movies reducer init") {
        Scenario("init") {
            var result: MoviesState? = null
            When("init is triggered") {
                result = MoviesReducer.init()
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            status = MoviesStatus.Init,
                            movies = PaginationData()
                        )
                    )
                }
            }
        }
    }

    Feature("Movies reducer reduce") {
        Scenario("load") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("a load action") {
                previousState = MoviesState(
                    status = MoviesStatus.Init,
                    movies = PaginationData()
                )
            }
            When("init is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Load)
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            status = MoviesStatus.Loading,
                            movies = PaginationData()
                        )
                    )
                }
            }
        }
        Scenario("reload") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("a reload action") {
                val paginationData = PaginationData<Movie>()
                val moviesPage = MoviesPage(SECOND_PAGE, firstPageItems)
                previousState = MoviesState(
                    status = MoviesStatus.Loaded,
                    movies = paginationData.addPage(moviesPage)
                )
            }
            When("init is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Reload)
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            status = MoviesStatus.Loaded,
                            movies = PaginationData()
                        )
                    )
                }
            }
        }
        Scenario("success") {
            val moviesPage = MoviesPage(SECOND_PAGE, firstPageItems)
            val paginationData = PaginationData<Movie>()
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("a success action") {
                previousState = MoviesState(
                    status = MoviesStatus.Loading,
                    movies = paginationData
                )
            }
            When("init is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Success(moviesPage))
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            status = MoviesStatus.Loaded,
                            movies = paginationData.addPage(moviesPage)
                        )
                    )
                }
            }
        }
        Scenario("error") {
            val paginationData = PaginationData<Movie>()
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("an error action") {
                val moviesPage = MoviesPage(SECOND_PAGE, firstPageItems)
                previousState = MoviesState(
                    status = MoviesStatus.Loading,
                    movies = paginationData.addPage(moviesPage)
                )
            }
            When("init is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Error)
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            status = MoviesStatus.Loaded,
                            movies = paginationData.addPage(MoviesPage(SECOND_PAGE, emptyList()))
                        )
                    )
                }
            }
        }
    }

    Feature("Movies reducer once") {
        Scenario("error") {
            @Suppress("LateinitUsage") lateinit var action: MoviesAction
            var result: MoviesSideEffect? = null
            Given("an error action") {
                action = MoviesAction.Error
            }
            When("once is triggered") {
                result = MoviesReducer.once(action)
            }
            Then("the side effect is the expected one") {
                expect { that(result).isEqualTo(MoviesSideEffect.Error) }
            }
        }
        Scenario("unexpected") {
            @Suppress("LateinitUsage") lateinit var action: MoviesAction
            Given("an unexpected action") {
                action = MoviesAction.Load
            }
            When("once is triggered, then throw illegal argument exception") {
                try {
                    MoviesReducer.once(action)
                } catch (e: Exception) {
                    expect { that(e).isA<IllegalArgumentException>() }
                }
            }
        }
    }

}) {

    companion object {

        private const val SECOND_PAGE = 2

    }

}
