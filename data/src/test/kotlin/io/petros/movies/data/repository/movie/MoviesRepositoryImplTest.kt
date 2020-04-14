package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.network.WebService
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

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val moviesPage = Result.Success(moviesPage())

    private val webServiceMock = mockk<WebService>()
    private val testedClass = MoviesRepositoryImpl(webServiceMock)

    @Test
    fun `when load movies is triggered, then the movies page is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns moviesPage.value

        val result = testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)

        expect { that(result).isEqualTo(moviesPage) }
    }

}
