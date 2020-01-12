package io.petros.movies.data.network.rest

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageRaw
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@Suppress("DeferredResultUnused")
class RestClientTest {

    companion object {

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val moviesResponse = MoviesResultPageRaw(0, 1, emptyList())

    private lateinit var testedClass: RestClient
    private val restApiMock = mockk<RestApi>()

    @Before
    fun setUp() {
        testedClass = RestClient(mockk(), restApiMock)
    }

    @Test
    fun `when load movies is triggered, then rest api triggers load movies`() = runBlocking {
        coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) } returns moviesResponse

        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        coVerify { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE) }
    }

}
