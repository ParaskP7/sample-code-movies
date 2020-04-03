package io.petros.movies.network.rest

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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

private const val RELEASE_DATE_GTE = "2018-08-01"
private const val RELEASE_DATE_LTE = "2018-08-31"

@ExperimentalCoroutinesApi
class RestClientSpek : CoroutineSpek({

    val restApiMock = mockk<RestApi>()

    @Suppress("StringLiteralDuplication")
    Feature("Rest client for movies") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("loading movies") {
            val moviesResponse = MoviesResultPageRaw(0, 1, emptyList())
            val movies = moviesResultPage(1, emptyList())
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

})
