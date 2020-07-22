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
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
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

        private const val MOVIE_ID = 419_704

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val date = Result.Success(Pair(MOVIE_YEAR, MOVIE_MONTH))
    private val movie = Result.Success(movie())
    private val movieEntity = MovieEntity.from(null, SECOND_PAGE, date.value.first, date.value.second, movie.value)

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
    fun `when load date is triggered, then the date is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { moviesDaoMock.firstMovie() } returns movieEntity

        val result = testedClass.loadDate()

        expect { that(result).isEqualTo(date) }
    }

    @Test
    fun `when load movie is triggered, then the movie is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { serviceMock.loadMovie(MOVIE_ID) } returns movie.value

        val result = testedClass.loadMovie(MOVIE_ID)

        expect { that(result).isEqualTo(movie) }
    }

}
