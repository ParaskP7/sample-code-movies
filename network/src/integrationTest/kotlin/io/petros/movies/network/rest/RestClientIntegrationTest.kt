package io.petros.movies.network.rest

import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.network.NetworkException
import io.petros.movies.test.domain.movie
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.TIMEOUT_MILLISECONDS
import io.petros.movies.test.utils.api
import io.petros.movies.test.utils.mockResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import java.util.*

class RestClientIntegrationTest {

    companion object {

        private const val MOVIES_DIR = "responses/movies"
        private const val MOVIES_PAGE_FILE = "$MOVIES_DIR/movies_page.json"

    }

    @Suppress("LateinitUsage") private lateinit var testedClass: RestClient
    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.start()

        testedClass = RestClient(api(server))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `given movies page response, when loading movies, then the movies page model is the expected one`() = runBlocking {
        server.enqueue(mockResponse(MOVIES_PAGE_FILE))

        val result = testedClass.loadMovies(null, null, null)

        expect { that(result).isEqualTo(expectedMoviesPage()) }
    }

    @Suppress("UNUSED_VARIABLE")
    @Test(expected = NetworkException::class)
    fun `given socket timeout exception, when loading movies, then throw network exception`() = runBlocking {
        server.enqueue(mockResponse(MOVIES_PAGE_FILE, TIMEOUT_MILLISECONDS * 2))

        val result = testedClass.loadMovies(null, null, null)
    }

    /* HELPER FUNCTIONS */

    private fun expectedMoviesPage(): MoviesPage {
        return moviesPage(
            nextPage = 2,
            movies = listOf(
                movie(
                    id = 419_704,
                    title = "Ad Astra",
                    releaseDate = GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time,
                    voteAverage = 6.0,
                    voteCount = 2958,
                    overview = "The near future, a time when both hope and hardships drive humanity to look to the stars " +
                            "and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut " +
                            "Roy McBride undertakes a mission across the immensity of space and its many perils to " +
                            "uncover the truth about a lost expedition that decades before boldly faced emptiness and " +
                            "silence in search of the unknown.",
                    backdrop = "http://image.tmdb.org/t/p/w500/5BwqwxMEjeFtdknRV792Svo0K1v.jpg"
                ),
                movie(
                    id = 38_700,
                    title = "Bad Boys for Life",
                    releaseDate = GregorianCalendar(2020, Calendar.JANUARY, 15).time,
                    voteAverage = 7.1,
                    voteCount = 2865,
                    overview = "Marcus and Mike are forced to confront new threats, career changes, and midlife crises as " +
                            "they join the newly created elite team AMMO of the Miami police department to take down the " +
                            "ruthless Armando Armas, the vicious leader of a Miami drug cartel.",
                    backdrop = null
                ),
                movie(
                    id = 454_626,
                    title = "Sonic the Hedgehog",
                    releaseDate = GregorianCalendar(2020, Calendar.FEBRUARY, 12).time,
                    voteAverage = 7.5,
                    voteCount = 2497,
                    overview = "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells " +
                            "the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this " +
                            "live-action adventure comedy, Sonic and his new best friend team up to defend the planet " +
                            "from the evil genius Dr. Robotnik and his plans for world domination.",
                    backdrop = "http://image.tmdb.org/t/p/w500/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg"
                )
            )
        )
    }

}
