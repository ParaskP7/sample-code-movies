package io.petros.movies.domain.model.movie

import io.petros.movies.test.domain.movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

class MovieSpek : Spek({

    Feature("Movie") {
        val testedClass by memoized { movie() }
        Scenario("release date") {
            var result: String? = null
            When("release date string is constructed") {
                result = testedClass.releaseDate()
            }
            Then("it is the expected one") {
                expect { that(result).isEqualTo("2019 (September)") }
            }
        }
        Scenario("vote") {
            var result: String? = null
            When("vote string is constructed") {
                result = testedClass.vote()
            }
            Then("it is the expected one") {
                expect { that(result).isEqualTo("6.0 ★ (2958)") }
            }
        }
    }

})
