package io.petros.movies.domain.model

import org.junit.Test
import strikt.api.expect
import strikt.assertions.isA

class ErrorsTest {

    data class MappableTestError(override val cause: Exception) : Result.Error(cause)

    class MappableTestException : Exception(), MappableError {
        override fun toError(): Result.Error = MappableTestError(this)

    }

    @Test
    fun `given mappable exception, when to error is triggered, then return a mappable error`() {
        val result = MappableTestException().toError()

        expect { that(result).isA<MappableTestError>() }
    }

    @Test
    fun `given non mappable exception, when to error is triggered, then return an unknown error`() {
        val result = Exception().toError()

        expect { that(result).isA<UnknownError>() }
    }

}
