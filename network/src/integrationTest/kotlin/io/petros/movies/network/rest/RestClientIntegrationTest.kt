package io.petros.movies.network.rest

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.test.domain.movie
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.MOCK_WEB_SERVER_PORT
import io.petros.movies.test.utils.api
import io.petros.movies.test.utils.mockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import java.util.*

@ExperimentalCoroutinesApi
class RestClientIntegrationTest {

    @Suppress("LateinitUsage")
    private lateinit var testedClass: RestClient
    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.start(MOCK_WEB_SERVER_PORT)

        testedClass = RestClient(api(server))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `given first movies page, when loading movies, then the movies page model is the expected one`() = runTest {
        server.enqueue(mockResponse(FIRST_MOVIES_PAGE_FILE))

        val result = testedClass.loadMovies(null, null, null)

        expect { that(result).isEqualTo(expectedFirstMoviesPage()) }
    }

    @Test
    fun `given second movies page, when loading movies, then the movies page model is the expected one`() = runTest {
        server.enqueue(mockResponse(SECOND_MOVIES_PAGE_FILE))

        val result = testedClass.loadMovies(null, null, null)

        expect { that(result).isEqualTo(expectedSecondMoviesPage()) }
    }

    @Test
    fun `given last movies page, when loading movies, then the movies page model is the expected one`() = runTest {
        server.enqueue(mockResponse(LAST_MOVIES_PAGE_FILE))

        val result = testedClass.loadMovies(null, null, null)

        expect { that(result).isEqualTo(expectedLastMoviesPage()) }
    }

    @Test
    fun `when loading movie, then the movie is the expected one`() = runTest {
        server.enqueue(mockResponse(MOVIE_FILE))

        val result = testedClass.loadMovie(454_626)

        expect { that(result).isEqualTo(expectedMovie()) }
    }

    /* HELPER FUNCTIONS */

    private fun expectedFirstMoviesPage(): MoviesPage {
        return moviesPage(
            movies = listOf(
                movie(
                    id = 419_704,
                    title = "Ad Astra",
                    releaseDate = GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time,
                    voteAverage = 6.0,
                    voteCount = 2958,
                    overview = "The near future, a time when both hope and hardships drive humanity to look to the " +
                            "stars and beyond. While a mysterious phenomenon menaces to destroy life on planet " +
                            "Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its " +
                            "many perils to uncover the truth about a lost expedition that decades before boldly " +
                            "faced emptiness and silence in search of the unknown.",
                    backdrop = "http://image.tmdb.org/t/p/w500/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
                ),
                movie(
                    id = 38_700,
                    title = "Bad Boys for Life",
                    releaseDate = GregorianCalendar(2020, Calendar.JANUARY, 15).time,
                    voteAverage = 7.1,
                    voteCount = 2865,
                    overview = "Marcus and Mike are forced to confront new threats, career changes, and midlife " +
                            "crises as they join the newly created elite team AMMO of the Miami police department to " +
                            "take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel.",
                    backdrop = null,
                ),
                movie(
                    id = 454_626,
                    title = "Sonic the Hedgehog",
                    releaseDate = GregorianCalendar(2020, Calendar.FEBRUARY, 12).time,
                    voteAverage = 7.5,
                    voteCount = 2497,
                    overview = "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog " +
                            "tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. " +
                            "In this live-action adventure comedy, Sonic and his new best friend team up to defend " +
                            "the planet from the evil genius Dr. Robotnik and his plans for world domination.",
                    backdrop = "http://image.tmdb.org/t/p/w500/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                ),
            ),
        )
    }

    private fun expectedSecondMoviesPage(): MoviesPage {
        return moviesPage(
            movies = listOf(
                movie(
                    id = 181_812,
                    title = "Star Wars: The Rise of Skywalker",
                    releaseDate = GregorianCalendar(2019, Calendar.DECEMBER, 18).time,
                    voteAverage = 6.5,
                    voteCount = 3946,
                    overview = "The surviving Resistance faces the First Order once again as the journey of Rey, " +
                            "Finn and Poe Dameron continues. With the power and knowledge of generations behind " +
                            "them, the final battle begins.",
                    backdrop = "http://image.tmdb.org/t/p/w500/99QDSTfr9bOqv1kbn8YRlynbgU.jpg",
                ),
                movie(
                    id = 495_764,
                    title = "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                    releaseDate = GregorianCalendar(2020, Calendar.FEBRUARY, 5).time,
                    voteAverage = 7.2,
                    voteCount = 2910,
                    overview = "Harley Quinn joins forces with a singer, an assassin and a police detective to help " +
                            "a young girl who had a hit placed on herafter she stole a rare diamond from a crime lord.",
                    backdrop = "http://image.tmdb.org/t/p/w500/uozb2VeD87YmhoUP1RrGWfzuCrr.jpg",
                ),
                movie(
                    id = 412_117,
                    title = "The Secret Life of Pets 2",
                    releaseDate = GregorianCalendar(2019, Calendar.MAY, 24).time,
                    voteAverage = 6.8,
                    voteCount = 1343,
                    overview = "Max the terrier must cope with some major life changes when his owner gets married " +
                            "and has a baby. When the family takes a trip to the countryside, nervous Max has " +
                            "numerous run-ins with canine-intolerant cows, hostile foxes and a scary turkey. Luckily " +
                            "for Max, he soon catches a break when he meets Rooster, a gruff farm dog who tries to " +
                            "cure the lovable pooch of his neuroses.",
                    backdrop = null,
                ),
            ),
        )
    }

    private fun expectedLastMoviesPage(): MoviesPage {
        return moviesPage(
            movies = listOf(
                movie(
                    id = 446_893,
                    title = "Trolls World Tour",
                    releaseDate = GregorianCalendar(2020, Calendar.MARCH, 12).time,
                    voteAverage = 7.7,
                    voteCount = 122,
                    overview = "Queen Poppy and Branch make a surprising discovery — there are other Troll worlds " +
                            "beyond their own, and their distinct differences create big clashes between these " +
                            "various tribes. When a mysterious threat puts all of the Trolls across the land in " +
                            "danger, Poppy, Branch, and their band of friends must embark on an epic quest to create " +
                            "harmony among the feuding Trolls to unite them against certain doom.",
                    backdrop = "http://image.tmdb.org/t/p/w500/qsxhnirlp7y4Ae9bd11oYJSX59j.jpg",
                ),
            ),
        )
    }

    private fun expectedMovie(): Movie {
        return movie(
            id = 454_626,
            title = "Sonic the Hedgehog",
            releaseDate = GregorianCalendar(2020, Calendar.FEBRUARY, 12).time,
            voteAverage = 7.5,
            voteCount = 2497,
            overview = "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells " +
                    "the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this " +
                    "live-action adventure comedy, Sonic and his new best friend team up to defend the planet " +
                    "from the evil genius Dr. Robotnik and his plans for world domination.",
            backdrop = "http://image.tmdb.org/t/p/w500/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
        )
    }

    companion object {

        private const val MOVIES_DIR = "responses/movies"
        private const val MOVIE_DIR = "responses/movie"

        private const val FIRST_MOVIES_PAGE_FILE = "$MOVIES_DIR/movies_page_first.json"
        private const val SECOND_MOVIES_PAGE_FILE = "$MOVIES_DIR/movies_page_second.json"
        private const val LAST_MOVIES_PAGE_FILE = "$MOVIES_DIR/movies_page_last.json"

        private const val MOVIE_FILE = "$MOVIE_DIR/movie.json"

    }

}
