package io.petros.movies.network

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.network.rest.RestApi
import io.petros.movies.network.rest.RestClient
import io.petros.movies.test.utils.CoroutineSpek
import io.petros.movies.utils.empty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isA
import java.io.IOException

private const val RELEASE_DATE_GTE = "2018-08-01"
private const val RELEASE_DATE_LTE = "2018-08-31"

@ExperimentalCoroutinesApi
class ExceptionSpek : CoroutineSpek({

    val restApiMock = mockk<RestApi>()

    @Suppress("TooGenericExceptionCaught")
    Feature("Network call with io exception") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("network call") {
            val exception = IOException(empty())
            Given("io exception") {
                coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) } throws exception
            }
            When("network call is triggered, then throw network exception") {
                runBlocking {
                    try {
                        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)
                    } catch (e: Exception) {
                        expect { that(e).isA<NetworkException>() }
                    }
                }
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    Feature("Network call with exception") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("network call") {
            val exception = Exception()
            Given("exception") {
                coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) } throws exception
            }
            When("network call is triggered, then throw exception") {
                runBlocking {
                    try {
                        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)
                    } catch (e: Exception) {
                        expect { that(e).isA<Exception>() }
                    }
                }
            }
        }
    }

    Feature("Exception") {
        Scenario("exception to error") {
            var exception: NetworkException? = null
            var result: Result.Error? = null
            Given("network exception") {
                exception = NetworkException(Exception())
            }
            When("to error is triggered") {
                result = exception?.toError()
            }
            Then("return a network error") {
                expect { that(result).isA<NetworkError>() }
            }
        }
    }

}) {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
