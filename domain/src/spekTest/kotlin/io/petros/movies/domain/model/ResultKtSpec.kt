package io.petros.movies.domain.model

import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.moviesPage
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class ResultKtSpec : Spek({

    @Suppress("LateinitUsage", "TooGenericExceptionThrown")
    Feature("As result") {
        Scenario("without exception") {
            lateinit var block: (() -> MoviesPage)
            var result: Result<MoviesPage>? = null
            Given("a block with no exception") {
                block = { moviesPage() }
            }
            When("as result is triggered") {
                result = asResult { block() }
            }
            Then("a success result is returned") {
                expect { that(result).isEqualTo(Result.Success(block())) }
            }
        }
        Scenario("with exception") {
            lateinit var block: (() -> MoviesPage)
            var result: Result<MoviesPage>? = null
            Given("a block with an exception") {
                block = { throw Exception() }
            }
            When("as result is triggered") {
                result = asResult { block() }
            }
            Then("an error result is returned") {
                expect { that(result).isA<Result.Error>() }
            }
        }
    }

})
