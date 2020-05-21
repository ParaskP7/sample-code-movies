package io.petros.movies.movie_details

import io.petros.movies.test.domain.movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class MovieDetailsStateSpek : Spek({

    val movie = movie()

    Feature("Movies reducer init") {
        Scenario("init") {
            var result: MovieDetailsState? = null
            When("init is triggered") {
                result = MovieDetailsReducer.init()
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MovieDetailsState(
                            status = MovieDetailsStatus.Init,
                            movie = null
                        )
                    )
                }
            }
        }
    }

    Feature("Movies reducer reduce") {
        Scenario("load") {
            @Suppress("LateinitUsage") lateinit var previousState: MovieDetailsState
            var result: MovieDetailsState? = null
            Given("a load action") {
                previousState = MovieDetailsState(
                    status = MovieDetailsStatus.Init,
                    movie = null
                )
            }
            When("reduce is triggered") {
                result = MovieDetailsReducer.reduce(previousState, MovieDetailsAction.Load)
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MovieDetailsState(
                            status = MovieDetailsStatus.Loading,
                            movie = null
                        )
                    )
                }
            }
        }
        Scenario("success") {
            @Suppress("LateinitUsage") lateinit var previousState: MovieDetailsState
            var result: MovieDetailsState? = null
            Given("a success action") {
                previousState = MovieDetailsState(
                    status = MovieDetailsStatus.Loading,
                    movie = null
                )
            }
            When("reduce is triggered") {
                result = MovieDetailsReducer.reduce(previousState, MovieDetailsAction.Success(movie))
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MovieDetailsState(
                            status = MovieDetailsStatus.Loaded,
                            movie = movie
                        )
                    )
                }
            }
        }
        Scenario("error") {
            @Suppress("LateinitUsage") lateinit var previousState: MovieDetailsState
            var result: MovieDetailsState? = null
            Given("an error action") {
                previousState = MovieDetailsState(
                    status = MovieDetailsStatus.Loading,
                    movie = null
                )
            }
            When("reduce is triggered") {
                result = MovieDetailsReducer.reduce(previousState, MovieDetailsAction.Error)
            }
            Then("the initial state is the expected one") {
                expect {
                    that(result).isEqualTo(
                        MovieDetailsState(
                            status = MovieDetailsStatus.Loaded,
                            movie = null
                        )
                    )
                }
            }
        }
    }

    Feature("Movies reducer once") {
        Scenario("error") {
            @Suppress("LateinitUsage") lateinit var action: MovieDetailsAction
            var result: MovieDetailsSideEffect? = null
            Given("an error action") {
                action = MovieDetailsAction.Error
            }
            When("once is triggered") {
                result = MovieDetailsReducer.once(action)
            }
            Then("the side effect is the expected one") {
                expect { that(result).isEqualTo(MovieDetailsSideEffect.Error) }
            }
        }
        Scenario("unexpected") {
            @Suppress("LateinitUsage") lateinit var action: MovieDetailsAction
            Given("an unexpected action") {
                action = MovieDetailsAction.Load
            }
            When("once is triggered, then throw illegal argument exception") {
                try {
                    MovieDetailsReducer.once(action)
                } catch (e: Exception) {
                    expect { that(e).isA<IllegalArgumentException>() }
                }
            }
        }
    }

})
