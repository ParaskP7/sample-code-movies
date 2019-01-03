package io.petros.movies.data.network.rest

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageResponse
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@Suppress("DeferredResultUnused")
class RestClientTest {

    companion object {

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

    private val moviesResponse = CompletableDeferred(MoviesResultPageResponse(0, 1, emptyList()))

    private lateinit var testedClass: RestClient
    private val restApiMock = mock<RestApi>()

    @Before
    fun setUp() {
        testedClass = RestClient(mock(), restApiMock)
    }

    @Test
    fun `When load movies is triggered, then rest api triggers load movies`() {
        runBlocking {
            whenever(restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE)).thenReturn(moviesResponse)

            testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

            verify(restApiMock).loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE)
        }
    }

}
