package io.petros.movies.data.repository.movie

import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.data.network.WebService
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
class MoviesRepositoryImplTest {

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private lateinit var testedClass: MoviesRepositoryImpl
    private val webServiceMock = mockk<WebService>()

    @Before
    fun setUp() {
        testedClass = MoviesRepositoryImpl(webServiceMock)
    }

    @Test
    fun `when load movies is triggered, then web service triggers load movies`() = runBlocking {
        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        coVerify { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
    }

}
