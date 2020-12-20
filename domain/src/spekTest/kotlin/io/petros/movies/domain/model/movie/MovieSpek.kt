package io.petros.movies.domain.model.movie

import io.petros.movies.test.domain.movie
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

class MovieSpek : Spek({

    Feature("Movie") {
        Scenario("null release date") {
            var testedClass: Movie? = null
            var result: String? = null
            Given("null release date") {
                testedClass = movie().copy(releaseDate = null)
            }
            When("release date string is constructed") {
                result = testedClass?.releaseDate()
            }
            Then("it is the expected one") {
                expect { that(result).isEqualTo("Not Available") }
            }
        }
        Scenario("non null release date") {
            var testedClass: Movie? = null
            var result: String? = null
            Given("non null release date") {
                testedClass = movie()
            }
            When("release date string is constructed") {
                result = testedClass?.releaseDate()
            }
            Then("it is the expected one") {
                expect { that(result).isEqualTo("2019 (September)") }
            }
        }
        Scenario("vote") {
            val testedClass = movie()
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
