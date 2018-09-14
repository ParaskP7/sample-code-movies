package io.petros.movies.data.network.rest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RestClientTest {

    companion object {

        private const val RELEASE_DATE_GTE = "2018-08-01"
        private const val RELEASE_DATE_LTE = "2018-08-31"

    }

    private lateinit var testedClass: RestClient
    private val restApiMock = mock<RestApi>()

    @Before
    fun setUp() {
        testedClass = RestClient(mock(), restApiMock)
    }

    @Test
    fun `When load movies is triggered, then rest api triggers load movies`() {
        whenever(restApiMock.loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE)).thenReturn(Single.just(mock()))

        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        verify(restApiMock).loadMovies(RELEASE_DATE_GTE, RELEASE_DATE_LTE, NEXT_PAGE)
    }

}
