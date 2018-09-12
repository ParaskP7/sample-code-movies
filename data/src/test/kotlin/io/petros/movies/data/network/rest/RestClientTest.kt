package io.petros.movies.data.network.rest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
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
        whenever(restApiMock.loadMovies()).thenReturn(Single.just(mock()))

        testedClass.loadMovies()

        verify(restApiMock).loadMovies()
    }

}
