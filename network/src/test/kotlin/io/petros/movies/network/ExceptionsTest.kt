package io.petros.movies.network

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.network.rest.RestApi
import io.petros.movies.network.rest.RestClient
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.utils.MainCoroutineScopeRule
import io.petros.movies.utils.empty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isA
import java.io.IOException

@ExperimentalCoroutinesApi
class ExceptionsTest {

    companion object {

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val restApiMock = mockk<RestApi>()
    private val testedClass = RestClient(restApiMock)

    @Test(expected = NetworkException::class)
    fun `given io exception, when network call is triggered, then throw network exception`() =
        coroutineScope.runBlockingTest {
            coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } throws IOException(empty())

            testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
        }

    @Test(expected = Exception::class)
    fun `given exception, when network call is triggered, then throw exception`() =
        coroutineScope.runBlockingTest {
            coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } throws Exception()

            testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
        }

    /* TO ERROR */

    @Test
    fun `given network exception, when to error is triggered, then return a network error`() {
        val result = NetworkException(Exception()).toError()

        expect { that(result).isA<NetworkError>() }
    }

}
