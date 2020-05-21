package io.petros.movies.network.rest

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.network.raw.movie.MoviesPageRaw
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class RestClientSpek : CoroutineSpek({

    val restApiMock = mockk<RestApi>()

    @Suppress("StringLiteralDuplication")
    Feature("Rest client for movies") {
        val testedClass by memoized { RestClient(restApiMock) }
        Scenario("loading movies") {
            val moviesPageRaw = MoviesPageRaw(0, 1, emptyList())
            val moviesPage = moviesPage(1, emptyList())
            var result: MoviesPage? = null
            Given("movies page response") {
                coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) } returns moviesPageRaw
            }
            When("load movies is triggered") {
                result = runBlocking { testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) }
            }
            Then("the movies page is the expected one") {
                expect { that(result).isEqualTo(moviesPage) }
            }
        }
    }

}) {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

}
