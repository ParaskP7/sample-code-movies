package io.petros.movies.domain.model.movie

import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

object MovieSpek : Spek({

    Feature("Movie") {
        val testedClass by memoized { provideMovie() }
        Scenario("release date") {
            lateinit var result: String
            When("release date string is constructed") {
                result = testedClass.releaseDate()
            }
            Then("it is the expected one") {
                expect { that(result).isEqualTo("2018 (September)") }
            }
        }
        Scenario("vote") {
            lateinit var result: String
            When("vote string is constructed") {
                result = testedClass.vote()
            }
            Then("it is the expected one") {
                expect { that(result).isEqualTo("10.0 ★ (100)") }
            }
        }
    }

})
