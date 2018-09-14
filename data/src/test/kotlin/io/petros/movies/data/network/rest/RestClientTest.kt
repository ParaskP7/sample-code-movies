package io.petros.movies.data.network.rest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RestClientTest {

    private lateinit var testedClass: RestClient
    private val restApiMock = mock<RestApi>()

    @Before
    fun setUp() {
        testedClass = RestClient(mock(), restApiMock)
    }

    @Test
    fun `When load movies is triggered, then rest api triggers load movies`() {
        whenever(restApiMock.loadMovies(NEXT_PAGE, MOVIE_YEAR)).thenReturn(Single.just(mock()))

        testedClass.loadMovies(NEXT_PAGE, MOVIE_YEAR)

        verify(restApiMock).loadMovies(NEXT_PAGE, MOVIE_YEAR)
    }

}
