package io.petros.movies.data.repository.movie

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.data.network.WebService
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {

    private lateinit var testedClass: MoviesRepositoryImpl
    private val webServiceMock = mock<WebService>()

    @Before
    fun setUp() {
        testedClass = MoviesRepositoryImpl(webServiceMock)
    }

    @Test
    fun `When load movies is triggered, then web service triggers load movies`() {
        testedClass.loadMovies()

        verify(webServiceMock).loadMovies()
    }

}
