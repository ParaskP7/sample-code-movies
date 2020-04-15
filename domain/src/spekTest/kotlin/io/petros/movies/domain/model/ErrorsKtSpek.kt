package io.petros.movies.domain.model

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isA

class ErrorsKtSpek : Spek({

    data class MappableTestError(override val cause: Exception) : Result.Error(cause)

    class MappableTestException : Exception(), MappableError {
        override fun toError(): Result.Error = MappableTestError(this)
    }

    Feature("Error") {
        Scenario("mappable exception to error") {
            var exception: Exception? = null
            var result: Result.Error? = null
            Given("mappable exception") {
                exception = MappableTestException()
            }
            When("to error is triggered") {
                result = exception?.toError()
            }
            Then("return a mappable error") {
                expect { that(result).isA<MappableTestError>() }
            }
        }
    }

    Feature("Error") {
        Scenario("non mappable exception to error") {
            var exception: Exception? = null
            var result: Result.Error? = null
            Given("non mappable exception") {
                exception = Exception()
            }
            When("to error is triggered") {
                result = exception?.toError()
            }
            Then("return an unknown error") {
                expect { that(result).isA<UnknownError>() }
            }
        }
    }

})
