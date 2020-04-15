package io.petros.movies.utils

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

class MonthOfYearSpek : Spek({

    Feature("Month of year from number") {
        Scenario("number zero") {
            var result: MonthOfYear? = null
            When("getting month of year from number") {
                result = MonthOfYear.from(0)
            }
            Then("the expect enum is returned") {
                expect { that(result).isEqualTo(MonthOfYear.JANUARY) }
            }
        }
        Scenario("number eleven") {
            var result: MonthOfYear? = null
            When("getting month of year from number") {
                result = MonthOfYear.from(11)
            }
            Then("the expect enum is returned") {
                expect { that(result).isEqualTo(MonthOfYear.DECEMBER) }
            }
        }
        Scenario("unknown number") {
            var result: MonthOfYear? = null
            When("getting month of year from number") {
                result = MonthOfYear.from(99)
            }
            Then("an unknown enum is returned") {
                expect { that(result).isEqualTo(MonthOfYear.UNKNOWN_MONTH) }
            }
        }
    }

    Feature("Month of year from label") {
        Scenario("january label") {
            var result: MonthOfYear? = null
            When("getting month of year from label") {
                result = MonthOfYear.from("January")
            }
            Then("the expect enum is returned") {
                expect { that(result).isEqualTo(MonthOfYear.JANUARY) }
            }
        }
        Scenario("december label") {
            var result: MonthOfYear? = null
            When("getting month of year from label") {
                result = MonthOfYear.from("December")
            }
            Then("the expect enum is returned") {
                expect { that(result).isEqualTo(MonthOfYear.DECEMBER) }
            }
        }
        Scenario("unknown label") {
            var result: MonthOfYear? = null
            When("getting month of year from label") {
                result = MonthOfYear.from("Undecimber")
            }
            Then("an unknown enum is returned") {
                expect { that(result).isEqualTo(MonthOfYear.UNKNOWN_MONTH) }
            }
        }
    }

})
