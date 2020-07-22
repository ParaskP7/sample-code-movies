package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.database.MoviesDatabase
import io.petros.movies.domain.model.Result
import io.petros.movies.network.MoviesService
import io.petros.movies.test.domain.movie
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

        private const val MOVIE_ID = 419_704

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val movie = Result.Success(movie())

    private val serviceMock = mockk<MoviesService>()
    private val moviesDatabaseMock = mockk<MoviesDatabase>()
    private val testedClass = MoviesRepositoryImpl(serviceMock, moviesDatabaseMock)

    @Test
    fun `when load movie is triggered, then the movie is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { serviceMock.loadMovie(MOVIE_ID) } returns movie.value

        val result = testedClass.loadMovie(MOVIE_ID)

        expect { that(result).isEqualTo(movie) }
    }

}
