package io.petros.movies.network

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.network.rest.RestApi
import io.petros.movies.network.rest.RestClient
import io.petros.movies.utils.empty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isA
import java.io.IOException

@ExperimentalCoroutinesApi
@Suppress("ThrowingExceptionsWithoutMessageOrCause")
class ExceptionsKtTest {

    private val restApiMock = mockk<RestApi>()
    private val testedClass = RestClient(restApiMock)

    @Test(expected = NetworkException::class)
    fun `given io exception, when network call is triggered, then throw network exception`() = runTest {
        coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) } throws
                IOException(empty())

        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)
    }

    @Test(expected = Exception::class)
    fun `given exception, when network call is triggered, then throw exception`() = runTest {
        coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) } throws Exception()

        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)
    }

    /* TO ERROR */

    @Test
    fun `given network exception, when to error is triggered, then return a network error`() {
        val result = NetworkException(Exception()).toError()

        expect { that(result).isA<NetworkError>() }
    }

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

}
