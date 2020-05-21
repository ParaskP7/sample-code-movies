package io.petros.movies.network.rest

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.network.raw.movie.MovieRaw
import io.petros.movies.network.raw.movie.MoviesPageRaw
import io.petros.movies.test.domain.movie
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo
import java.util.*

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
        Scenario("loading movie") {
            val title = "Ad Astra"
            val releaseDate = "2019-09-17"
            val voteAverage = 6.0
            val voteCount = 2958
            val overview = "The near future, a time when both hope and hardships drive humanity to look to the stars " +
                    "and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut " +
                    "Roy McBride undertakes a mission across the immensity of space and its many perils to " +
                    "uncover the truth about a lost expedition that decades before boldly faced emptiness and " +
                    "silence in search of the unknown."
            val backdrop = "http://image.tmdb.org/t/p/w500/5BwqwxMEjeFtdknRV792Svo0K1v.jpg"
            val movieRaw = MovieRaw(voteCount, MOVIE_ID, voteAverage, title, backdrop, overview, releaseDate)
            val movie = movie(
                MOVIE_ID,
                title,
                GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time,
                voteAverage,
                voteCount,
                overview,
                MovieRaw.IMAGE_URL_PREFIX + backdrop
            )
            var result: Movie? = null
            Given("movies page response") {
                coEvery { restApiMock.loadMovie(MOVIE_ID) } returns movieRaw
            }
            When("load movie is triggered") {
                result = runBlocking { testedClass.loadMovie(MOVIE_ID) }
            }
            Then("the movie is the expected one") {
                expect { that(result).isEqualTo(movie) }
            }
        }
    }

}) {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_ID = 419_704
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

}
