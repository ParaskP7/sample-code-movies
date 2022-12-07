package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.petros.movies.database.MoviesDatabase
import io.petros.movies.database.dao.MoviesDao
import io.petros.movies.database.entity.MovieEntity
import io.petros.movies.domain.model.Result
import io.petros.movies.network.MoviesService
import io.petros.movies.test.domain.movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    private val date = Result.Success(MOVIE_YEAR to MOVIE_MONTH)
    private val movie = Result.Success(movie())
    private val movieEntity = MovieEntity.from(
        prevPage = null,
        nextPage = SECOND_PAGE,
        year = date.value.first,
        month = date.value.second,
        movie = movie.value
    )
    private val movieEntityStream = flow<MovieEntity> { movieEntity }

    private val moviesDaoMock = mockk<MoviesDao>()

    private val serviceMock = mockk<MoviesService>()
    private val databaseMock = mockk<MoviesDatabase>()
    private val testedClass = MoviesRepositoryImpl(serviceMock, databaseMock)

    @Before
    fun setUp() {
        mockDatabase()
    }

    private fun mockDatabase() {
        every { databaseMock.moviesDao() } returns moviesDaoMock
    }

    @Test
    fun `when load date is triggered, then the date is the expected one`() = runTest {
        coEvery { moviesDaoMock.firstMovie() } returns movieEntity

        val result = testedClass.loadDate()

        expect { that(result).isEqualTo(date) }
    }

    @Test
    fun `when load movie is triggered, then the movie stream is the expected one`() = runTest {
        every { moviesDaoMock.movie(MOVIE_ID) } returns movieEntityStream

        val result = testedClass.loadMovieStream(MOVIE_ID)

        result.collectLatest { expect { that(it).isEqualTo(movie) } }
    }

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

        private const val MOVIE_ID = 419_704

    }

}
