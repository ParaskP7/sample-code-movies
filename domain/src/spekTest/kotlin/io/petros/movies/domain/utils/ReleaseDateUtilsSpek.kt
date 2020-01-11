package io.petros.movies.domain.utils

import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

object ReleaseDateUtilsSpek : Spek({

    Feature("Release date utils with no year") {
        Scenario("greater than or equal") {
            var result: String? = null
            When("converting to release date") {
                result = releaseDateGte(null, null)
            }
            Then("null is returned") {
                expect { that(result).isNull() }
            }
        }
        Scenario("less than or equal") {
            var result: String? = null
            When("converting to release date") {
                result = releaseDateLte(null, null)
            }
            Then("null is returned") {
                expect { that(result).isNull() }
            }
        }
    }

    Feature("Release date utils with year but no month") {
        Scenario("greater than or equal") {
            var result: String? = null
            When("converting to release date") {
                result = releaseDateGte(MOVIE_YEAR, null)
            }
            Then("the expected range is returned") {
                expect { that(result).isEqualTo("2018-01-01") }
            }
        }
        Scenario("less than or equal") {
            var result: String? = null
            When("converting to release date") {
                result = releaseDateLte(MOVIE_YEAR, null)
            }
            Then("the expected range is returned") {
                expect { that(result).isEqualTo("2018-12-31") }
            }
        }
    }

    Feature("Release date utils with year and month") {
        Scenario("greater than or equal") {
            var result: String? = null
            When("converting to release date") {
                result = releaseDateGte(MOVIE_YEAR, MOVIE_MONTH)
            }
            Then("the expected range is returned") {
                expect { that(result).isEqualTo("2018-08-01") }
            }
        }
        Scenario("less than or equal") {
            var result: String? = null
            When("converting to release date") {
                result = releaseDateLte(MOVIE_YEAR, MOVIE_MONTH)
            }
            Then("the expected range is returned") {
                expect { that(result).isEqualTo("2018-08-31") }
            }
        }
    }

})
