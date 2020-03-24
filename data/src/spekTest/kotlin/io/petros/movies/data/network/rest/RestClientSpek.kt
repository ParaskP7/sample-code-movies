package io.petros.movies.data.network.rest

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageRaw
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature

private const val RELEASE_DATE_GTE = "2018-08-01"
private const val RELEASE_DATE_LTE = "2018-08-31"

@ExperimentalCoroutinesApi
class RestClientSpek : CoroutineSpek({

    val moviesResponse = MoviesResultPageRaw(0, 1, emptyList())
    val restApiMock = mockk<RestApi>()
    coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } returns moviesResponse

    Feature("Rest client for movies") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("loading movies") {
            When("load movies is triggered") {
                runBlocking { testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
            }
            Then("rest api triggers load movies") {
                coVerify { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) }
            }
        }
    }

})
