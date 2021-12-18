package io.petros.movies.data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult
import androidx.room.withTransaction
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.petros.movies.data.repository.movie.MoviesRemoteMediator.Companion.MOVIES_STARTING_PAGE
import io.petros.movies.database.MoviesDatabase
import io.petros.movies.database.dao.MoviesDao
import io.petros.movies.database.entity.MovieEntity
import io.petros.movies.network.MoviesService
import io.petros.movies.network.NetworkException
import io.petros.movies.test.domain.movie
import io.petros.movies.test.domain.moviesPage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
@OptIn(ExperimentalPagingApi::class)
@Suppress("ThrowingExceptionsWithoutMessageOrCause")
class MoviesRemoteMediatorTest {

    private val firstPageMovies = listOf(movie(id = 1), movie(id = 2), movie(id = 3))
    private val firstPageMovieEntities = listOf(
        MovieEntity.from(null, SECOND_PAGE, MOVIE_YEAR, MOVIE_MONTH, firstPageMovies[0]),
        MovieEntity.from(null, SECOND_PAGE, MOVIE_YEAR, MOVIE_MONTH, firstPageMovies[1]),
        MovieEntity.from(null, SECOND_PAGE, MOVIE_YEAR, MOVIE_MONTH, firstPageMovies[2])
    )
    private val firstPage = Page(
        data = firstPageMovieEntities,
        prevKey = null,
        nextKey = SECOND_PAGE,
    )

    private val secondPageMovies = listOf(movie(id = 4), movie(id = 5), movie(id = 6))
    private val secondPageMovieEntities = listOf(
        MovieEntity.from(FIRST_PAGE, THIRD_PAGE, MOVIE_YEAR, MOVIE_MONTH, secondPageMovies[0]),
        MovieEntity.from(FIRST_PAGE, THIRD_PAGE, MOVIE_YEAR, MOVIE_MONTH, secondPageMovies[1]),
        MovieEntity.from(FIRST_PAGE, THIRD_PAGE, MOVIE_YEAR, MOVIE_MONTH, secondPageMovies[2])
    )
    private val secondPage = Page(
        data = secondPageMovieEntities,
        prevKey = SECOND_PAGE,
        nextKey = THIRD_PAGE,
    )

    private val pagingStateMock = mockk<PagingState<Int, MovieEntity>>()
    private val moviesDaoMock = mockk<MoviesDao>()

    private val serviceMock = mockk<MoviesService>()
    private val databaseMock = mockk<MoviesDatabase>()
    private val testedClass = MoviesRemoteMediator(serviceMock, databaseMock, MOVIE_YEAR, MOVIE_MONTH)

    @Before
    fun setUp() {
        mockDatabase()
    }

    private fun mockDatabase() {
        mockkStatic(ROOM_DATABASE_KT_STATIC_MOCK)
        val transactionLambda = slot<suspend () -> Unit>()
        coEvery { databaseMock.withTransaction(capture(transactionLambda)) } coAnswers {
            transactionLambda.captured.invoke()
        }
        every { databaseMock.moviesDao() } returns moviesDaoMock
    }

    /* REFRESH */

    @Test
    fun `given next page for refresh, when load is triggered, then success with end pagination unreached`() = runTest {
        val position = 0
        every { pagingStateMock.anchorPosition } returns position
        every { pagingStateMock.closestItemToPosition(position) } returns firstPageMovieEntities[0]

        val result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Success

        expect { that(result.endOfPaginationReached).isEqualTo(false) }
        coVerify(exactly = 0) { serviceMock.loadMovies(any(), any(), any()) }
    }

    @Test
    fun `given no next page for refresh, when load is triggered, then trigger initial load movies from network`() =
        runTest {
            every { pagingStateMock.anchorPosition } returns null

            testedClass.load(LoadType.REFRESH, pagingStateMock)

            coVerify { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) }
        }

    @Test
    fun `given empty list of movies for refresh, when load movies returns, then success with end pagination reached`() =
        runTest {
            every { pagingStateMock.anchorPosition } returns null
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } returns
                    moviesPage(emptyList())

            val result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Success

            expect { that(result.endOfPaginationReached).isEqualTo(true) }
        }

    @Test
    fun `given list of movies for refresh, when load movies returns, then do clear movies from database`() = runTest {
        every { pagingStateMock.anchorPosition } returns null
        coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } returns
                moviesPage(firstPageMovies)

        testedClass.load(LoadType.REFRESH, pagingStateMock)

        coVerify { moviesDaoMock.clear() }
    }

    @Test
    fun `given list of movies for refresh, when load movies returns, then insert movies into database`() = runTest {
        every { pagingStateMock.anchorPosition } returns null
        coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } returns
                moviesPage(firstPageMovies)

        testedClass.load(LoadType.REFRESH, pagingStateMock)

        coVerify { moviesDaoMock.insert(firstPageMovieEntities) }
    }

    @Test
    fun `given list of movies for refresh, when load movies returns, then success with end pagination unreached`() =
        runTest {
            every { pagingStateMock.anchorPosition } returns null
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } returns
                    moviesPage(firstPageMovies)

            val result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Success

            expect { that(result.endOfPaginationReached).isEqualTo(false) }
        }

    /* APPEND */

    @Test
    fun `given append, when load is triggered, then trigger load movies from network`() = runTest {
        every { pagingStateMock.pages } returns listOf(firstPage)

        testedClass.load(LoadType.APPEND, pagingStateMock)

        coVerify { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) }
    }

    @Test
    fun `given empty list of movies for append, when load movies returns, then success with end pagination reached`() =
        runTest {
            every { pagingStateMock.pages } returns listOf(firstPage)
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns moviesPage(emptyList())

            val result = testedClass.load(LoadType.APPEND, pagingStateMock) as MediatorResult.Success

            expect { that(result.endOfPaginationReached).isEqualTo(true) }
        }

    @Test
    fun `given list of movies for append, when load movies returns, then do not clear movies from database`() =
        runTest {
            every { pagingStateMock.pages } returns listOf(firstPage)
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns
                    moviesPage(secondPageMovies)

            testedClass.load(LoadType.APPEND, pagingStateMock)

            coVerify(exactly = 0) { moviesDaoMock.clear() }
        }

    @Test
    fun `given list of movies for append, when load movies returns, then insert movies into database`() = runTest {
        every { pagingStateMock.pages } returns listOf(firstPage)
        coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns
                moviesPage(secondPageMovies)

        testedClass.load(LoadType.APPEND, pagingStateMock)

        coVerify { moviesDaoMock.insert(secondPageMovieEntities) }
    }

    @Test
    fun `given list of movies for append, when load movies returns, then success with end pagination unreached`() =
        runTest {
            every { pagingStateMock.pages } returns listOf(firstPage)
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns
                    moviesPage(secondPageMovies)

            val result = testedClass.load(LoadType.APPEND, pagingStateMock) as MediatorResult.Success

            expect { that(result.endOfPaginationReached).isEqualTo(false) }
        }

    /* PREPEND */

    @Test
    fun `given no previous page for prepend, when load is triggered, then success with end pagination reached`() =
        runTest {
            every { pagingStateMock.pages } returns emptyList()

            val result = testedClass.load(LoadType.PREPEND, pagingStateMock) as MediatorResult.Success

            expect { that(result.endOfPaginationReached).isEqualTo(true) }
            coVerify(exactly = 0) { serviceMock.loadMovies(any(), any(), any()) }
        }

    @Test
    fun `given previous page for prepend, when load is triggered, then trigger load movies from network`() = runTest {
        every { pagingStateMock.pages } returns listOf(secondPage)

        testedClass.load(LoadType.PREPEND, pagingStateMock)

        coVerify { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) }
    }

    @Test
    fun `given empty list of movies for prepend, when load movies returns, then success with end pagination reached`() =
        runTest {
            every { pagingStateMock.pages } returns listOf(secondPage)
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) } returns moviesPage(emptyList())

            val result = testedClass.load(LoadType.PREPEND, pagingStateMock) as MediatorResult.Success

            expect { that(result.endOfPaginationReached).isEqualTo(true) }
        }

    @Test
    fun `given list of movies for prepend, when load movies returns, then do not clear movies from database`() =
        runTest {
            every { pagingStateMock.pages } returns listOf(secondPage)
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) } returns moviesPage(firstPageMovies)

            testedClass.load(LoadType.PREPEND, pagingStateMock)

            coVerify(exactly = 0) { moviesDaoMock.clear() }
        }

    @Test
    fun `given list of movies for prepend, when load movies returns, then insert movies into database`() = runTest {
        every { pagingStateMock.pages } returns listOf(secondPage)
        coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) } returns moviesPage(firstPageMovies)

        testedClass.load(LoadType.PREPEND, pagingStateMock)

        coVerify { moviesDaoMock.insert(firstPageMovieEntities) }
    }

    @Test
    fun `given list of movies for prepend, when load movies returns, then success with end pagination unreached`() =
        runTest {
            every { pagingStateMock.pages } returns listOf(secondPage)
            coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) } returns moviesPage(firstPageMovies)

            val result = testedClass.load(LoadType.PREPEND, pagingStateMock) as MediatorResult.Success

            expect { that(result.endOfPaginationReached).isEqualTo(false) }
        }

    /* ERROR */

    @Test
    fun `given exception, when load is triggered, then error with exception`() = runTest {
        val exception = NetworkException(Exception())
        every { pagingStateMock.anchorPosition } returns null
        coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } throws exception

        val result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Error

        expect { that(result.throwable).isEqualTo(exception) }
    }

    companion object {

        private const val ROOM_DATABASE_KT_STATIC_MOCK = "androidx.room.RoomDatabaseKt"

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

        private const val FIRST_PAGE = 1
        private const val SECOND_PAGE = 2
        private const val THIRD_PAGE = 3

    }

}
