package io.petros.movies.utils

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import java.text.ParseException
import java.util.*

class KotlinExtensionsKtSpek : Spek({

    Feature("Single characters") {
        Scenario("empty") {
            var result: String? = null
            When("empty is triggered") {
                result = empty()
            }
            Then("an empty string is returned") {
                expect { that(result).isEqualTo("") }
            }
        }
        Scenario("space") {
            var result: String? = null
            When("space is triggered") {
                result = space()
            }
            Then("a space string is returned") {
                expect { that(result).isEqualTo(" ") }
            }
        }
        Scenario("left parentheses") {
            var result: String? = null
            When("left parentheses is triggered") {
                result = leftParentheses()
            }
            Then("a left parentheses string is returned") {
                expect { that(result).isEqualTo("(") }
            }
        }
        Scenario("right parentheses") {
            var result: String? = null
            When("right parentheses is triggered") {
                result = rightParentheses()
            }
            Then("a right parentheses string is returned") {
                expect { that(result).isEqualTo(")") }
            }
        }
        Scenario("star") {
            var result: String? = null
            When("star is triggered") {
                result = star()
            }
            Then("a star string is returned") {
                expect { that(result).isEqualTo("★") }
            }
        }
        Scenario("colon") {
            var result: String? = null
            When("colon is triggered") {
                result = colon()
            }
            Then("a colon string is returned") {
                expect { that(result).isEqualTo(":") }
            }
        }
    }

    Feature("Parentheses") {
        Scenario("string") {
            var string: String? = null
            var result: String? = null
            Given("string") {
                string = "word"
            }
            When("with parentheses is triggered") {
                result = string?.withParentheses()
            }
            Then("the string is enclosed in parentheses") {
                expect { that(result).isEqualTo("($string)") }
            }
        }
        Scenario("integer") {
            var integer: Int? = null
            var result: String? = null
            Given("integer") {
                integer = 1
            }
            When("with parentheses is triggered") {
                result = integer?.withParentheses()
            }
            Then("the integer is enclosed in parentheses") {
                expect { that(result).isEqualTo("($integer)") }
            }
        }
    }

    Feature("date") {
        Scenario("valid to date") {
            var date: String? = null
            var result: Date? = null
            Given("valid date as string") {
                date = "2019-09-17"
            }
            When("to date is triggered") {
                result = date?.toDate(MOVIE_DATE_FORMAT)
            }
            Then("the expect date is returned") {
                expect { that(result).isEqualTo(GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time) }
            }
        }
        Scenario("invalid to date") {
            var date: String? = null
            Given("invalid date as string") {
                date = "09-17"
            }
            When("to date is triggered, then throw parse exception") {
                try {
                    date?.toDate(MOVIE_DATE_FORMAT)
                } catch (e: Exception) {
                    expect { that(e).isA<ParseException>() }
                }
            }
        }
        Scenario("to date") {
            var date: Date? = null
            var result: String? = null
            Given("date") {
                date = GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time
            }
            When("to date is triggered") {
                result = date?.toDate(MOVIE_DATE_FORMAT)
            }
            Then("the expect date is returned") {
                expect { that(result).isEqualTo("2019-09-17") }
            }
        }
        Scenario("to year") {
            val year = 2019
            var date: Date? = null
            var result: Int? = null
            Given("date") {
                date = GregorianCalendar(year, Calendar.SEPTEMBER, 17).time
            }
            When("to year is triggered") {
                result = date?.toYear()
            }
            Then("the expect year is returned") {
                expect { that(result).isEqualTo(year) }
            }
        }
        Scenario("to month") {
            val month = MonthOfYear.SEPTEMBER
            var date: Date? = null
            var result: String? = null
            Given("date") {
                date = GregorianCalendar(2019, month.number, 17).time
            }
            When("to month is triggered") {
                result = date?.toMonth()
            }
            Then("the expect month is returned") {
                expect { that(result).isEqualTo(month.label) }
            }
        }
    }

})
