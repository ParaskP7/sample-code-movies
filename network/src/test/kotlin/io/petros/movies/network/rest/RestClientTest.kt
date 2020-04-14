package io.petros.movies.network.rest

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.network.raw.movie.MoviesPageRaw
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class RestClientTest {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val restApiMock = mockk<RestApi>()
    private val testedClass = RestClient(restApiMock)

    @Test
    fun `when load movies is triggered, then rest api triggers load movies`() = coroutineScope.runBlockingTest {
        val moviesPageRaw = MoviesPageRaw(0, 1, emptyList())
        coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) } returns moviesPageRaw

        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)

        coVerify { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) }
    }

    @Test
    fun `when load movies is triggered, then the movies page is the expected one`() = coroutineScope.runBlockingTest {
        val moviesPageRaw = MoviesPageRaw(0, 1, emptyList())
        val moviesPage = moviesPage(1, emptyList())
        coEvery { restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, SECOND_PAGE) } returns moviesPageRaw

        val result = testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)

        expect { that(result).isEqualTo(moviesPage) }
    }

}
