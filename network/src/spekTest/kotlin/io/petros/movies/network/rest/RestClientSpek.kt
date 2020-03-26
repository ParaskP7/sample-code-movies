package io.petros.movies.network.rest

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.network.raw.movie.MoviesResultPageRaw
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.moviesResultPage
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo
import java.net.ConnectException
import java.net.UnknownHostException

private const val RELEASE_DATE_GTE = "2018-08-01"
private const val RELEASE_DATE_LTE = "2018-08-31"

@ExperimentalCoroutinesApi
class RestClientSpek : CoroutineSpek({

    val moviesResponse = MoviesResultPageRaw(0, 1, emptyList())
    val movies = moviesResultPage(1, emptyList())
    val restApiMock = mockk<RestApi>()

    @Suppress("StringLiteralDuplication")
    Feature("Rest client for movies") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("loading movies") {
            var result: MoviesResultPage? = null
            Given("movies response") {
                coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } returns moviesResponse
            }
            When("load movies is triggered") {
                result = runBlocking { testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
            }
            Then("rest api triggers load movies") {
                coVerify { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) }
            }
            Then("the movies result page is the expected one") {
                expect { that(result).isEqualTo(movies) }
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    Feature("Rest client for movies with unknown hosts exception") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("loading movies") {
            val exception = UnknownHostException()
            Given("unknown host exception") {
                coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } throws exception
            }
            When("load movies is triggered, then throw load movies use case error") {
                runBlocking {
                    try {
                        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
                    } catch (t: Throwable) {
                        expect { that(t.cause).isEqualTo(LoadMoviesUseCase.Error(exception).cause) }
                    }
                }
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    Feature("Rest client for movies with connect exception") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("loading movies") {
            val exception = ConnectException()
            Given("connection exception") {
                coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } throws exception
            }
            When("load movies is triggered, then throw load movies use case error") {
                runBlocking {
                    try {
                        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
                    } catch (t: Throwable) {
                        expect { that(t.cause).isEqualTo(LoadMoviesUseCase.Error(exception).cause) }
                    }
                }
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    Feature("Rest client for movies with exception") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("loading movies") {
            val exception = Exception()
            Given("exception") {
                coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } throws exception
            }
            When("load movies is triggered, then throw exception") {
                runBlocking {
                    try {
                        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
                    } catch (t: Throwable) {
                        expect { that(t.cause).isEqualTo(exception.cause) }
                    }
                }
            }
        }
    }

})
