package io.petros.movies.domain.model

import io.petros.movies.test.domain.moviesPage
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class ResultKtTest {

    @Test
    fun `given a block with no exception, when as result is triggered, then a success result is returned`() {
        val block = { moviesPage() }

        val result = asResult { block() }

        expect { that(result).isEqualTo(Result.Success(block())) }
    }

    @Test
    @Suppress("TooGenericExceptionThrown")
    fun `given a block with an exception, when as result is triggered, then an error result is returned`() {
        val block = { throw Exception() }

        val result = asResult { block() }

        expect { that(result).isA<Result.Error>() }
    }

}
