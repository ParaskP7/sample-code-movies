package io.petros.movies.movies

import androidx.paging.LoadType
import androidx.paging.PagingData
import io.mockk.mockk
import io.petros.movies.domain.model.movie.Movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class MoviesStateSpek : Spek({

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
                            year = null,
                            month = null,
                            movies = PagingData.empty(),
                        )
                    )
                }
            }
        }
    }

    Feature("Date reducer reduce") {
        Scenario("date success") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("an date success action") {
                previousState = MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            }
            When("reduce is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.DateSuccess(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the new state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            movies = PagingData.empty(),
                        )
                    )
                }
            }
        }
        Scenario("date error") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("an date error action") {
                previousState = MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = PagingData.empty(),
                )
            }
            When("reduce is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.DateError)
            }
            Then("the new state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            movies = PagingData.empty(),
                        )
                    )
                }
            }
        }
    }

    Feature("Movies reducer reduce") {
        Scenario("idle") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("an idle action") {
                previousState = MoviesState(
                    year = null,
                    month = null,
                    movies = PagingData.empty(),
                )
            }
            When("reduce is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Idle(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the new state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            movies = PagingData.empty(),
                        )
                    )
                }
            }
        }
        Scenario("reload") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            var result: MoviesState? = null
            Given("a reload action") {
                previousState = MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = mockk(),
                )
            }
            When("reduce is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Reload(MOVIE_YEAR, MOVIE_MONTH))
            }
            Then("the new state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            movies = PagingData.empty(),
                        )
                    )
                }
            }
        }
        Scenario("success") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            val movies = PagingData.empty<Movie>()
            var result: MoviesState? = null
            Given("a success action") {
                previousState = MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = mockk(),
                )
            }
            When("reduce is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Success(movies))
            }
            Then("the new state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            movies = movies,
                        )
                    )
                }
            }
        }
        Scenario("error") {
            @Suppress("LateinitUsage") lateinit var previousState: MoviesState
            val movies = PagingData.empty<Movie>()
            var result: MoviesState? = null
            Given("an error action") {
                previousState = MoviesState(
                    year = MOVIE_YEAR,
                    month = MOVIE_MONTH,
                    movies = movies,
                )
            }
            When("reduce is triggered") {
                result = MoviesReducer.reduce(previousState, MoviesAction.Error(LoadType.APPEND))
            }
            Then("the new state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MoviesState(
                            year = MOVIE_YEAR,
                            month = MOVIE_MONTH,
                            movies = movies,
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
            Given("a refresh error action") {
                action = MoviesAction.Error(LoadType.REFRESH)
            }
            When("once is triggered") {
                result = MoviesReducer.once(action)
            }
            Then("the side effect is the expected one") {
                expect { that(result).isEqualTo(MoviesSideEffect.MoviesRefreshError) }
            }
        }
        Scenario("error") {
            @Suppress("LateinitUsage") lateinit var action: MoviesAction
            var result: MoviesSideEffect? = null
            Given("an append error action") {
                action = MoviesAction.Error(LoadType.APPEND)
            }
            When("once is triggered") {
                result = MoviesReducer.once(action)
            }
            Then("the side effect is the expected one") {
                expect { that(result).isEqualTo(MoviesSideEffect.MoviesAppendError) }
            }
        }
        Scenario("error") {
            @Suppress("LateinitUsage") lateinit var action: MoviesAction
            var result: MoviesSideEffect? = null
            Given("a prepend error action") {
                action = MoviesAction.Error(LoadType.PREPEND)
            }
            When("once is triggered") {
                result = MoviesReducer.once(action)
            }
            Then("the side effect is the expected one") {
                expect { that(result).isEqualTo(MoviesSideEffect.MoviesPrependError) }
            }
        }
        Scenario("unexpected") {
            @Suppress("LateinitUsage") lateinit var action: MoviesAction
            Given("an unexpected action") {
                action = MoviesAction.Reload(null, null)
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

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
