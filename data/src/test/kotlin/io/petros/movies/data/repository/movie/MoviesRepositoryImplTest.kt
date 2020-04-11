package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.network.WebService
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val moviesPage = Result.Success(moviesPage())

    private val webServiceMock = mockk<WebService>()
    private val testedClass = MoviesRepositoryImpl(webServiceMock)

    @Test
    fun `when load movies is triggered, then the movies page is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) } returns moviesPage.value

        val result = testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        expect { that(result).isEqualTo(moviesPage) }
    }

}
