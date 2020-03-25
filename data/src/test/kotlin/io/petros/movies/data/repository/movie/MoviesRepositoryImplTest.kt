package io.petros.movies.data.repository.movie

import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.network.WebService
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val webServiceMock = mockk<WebService>()
    private val testedClass = MoviesRepositoryImpl(webServiceMock)

    @Test
    fun `when load movies is triggered, then web service triggers load movies`() = coroutineScope.runBlockingTest {
        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        coVerify { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
    }

}
