package io.petros.movies.data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
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
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

private const val ROOM_DATABASE_KT_STATIC_MOCK = "androidx.room.RoomDatabaseKt"

@ExperimentalCoroutinesApi
private fun setupDatabase(databaseMock: MoviesDatabase, moviesDaoMock: MoviesDao) {
    mockkStatic(ROOM_DATABASE_KT_STATIC_MOCK)
    val transactionLambda = slot<suspend () -> Unit>()
    coEvery { databaseMock.withTransaction(capture(transactionLambda)) } coAnswers {
        transactionLambda.captured.invoke()
    }
    every { databaseMock.moviesDao() } returns moviesDaoMock
}

@ExperimentalCoroutinesApi
@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediatorSpek : CoroutineSpek({

    val firstPageMovies = listOf(movie(id = 1), movie(id = 2), movie(id = 3))
    val firstPageMovieEntities = listOf(
        MovieEntity.from(null, SECOND_PAGE, MOVIE_YEAR, MOVIE_MONTH, firstPageMovies[0]),
        MovieEntity.from(null, SECOND_PAGE, MOVIE_YEAR, MOVIE_MONTH, firstPageMovies[1]),
        MovieEntity.from(null, SECOND_PAGE, MOVIE_YEAR, MOVIE_MONTH, firstPageMovies[2])
    )
    val firstPage = PagingSource.LoadResult.Page(
        data = firstPageMovieEntities,
        prevKey = null,
        nextKey = SECOND_PAGE,
    )

    val secondPageMovies = listOf(movie(id = 4), movie(id = 5), movie(id = 6))
    val secondPageMovieEntities = listOf(
        MovieEntity.from(FIRST_PAGE, THIRD_PAGE, MOVIE_YEAR, MOVIE_MONTH, secondPageMovies[0]),
        MovieEntity.from(FIRST_PAGE, THIRD_PAGE, MOVIE_YEAR, MOVIE_MONTH, secondPageMovies[1]),
        MovieEntity.from(FIRST_PAGE, THIRD_PAGE, MOVIE_YEAR, MOVIE_MONTH, secondPageMovies[2])
    )
    val secondPage = PagingSource.LoadResult.Page(
        data = secondPageMovieEntities,
        prevKey = SECOND_PAGE,
        nextKey = THIRD_PAGE,
    )

    Feature("Movies remote mediator for refresh") {
        val pagingStateMock = mockk<PagingState<Int, MovieEntity>>()
        val moviesDaoMock = mockk<MoviesDao>()

        val serviceMock = mockk<MoviesService>()
        val databaseMock = mockk<MoviesDatabase>()
        val testedClass by memoized {
            MoviesRemoteMediator(serviceMock, databaseMock, MOVIE_YEAR, MOVIE_MONTH)
        }
        Scenario("with next page") {
            var result: MediatorResult.Success? = null
            Given("next page") {
                setupDatabase(databaseMock, moviesDaoMock)
                val position = 0
                every { pagingStateMock.anchorPosition } returns position
                every { pagingStateMock.closestItemToPosition(position) } returns firstPageMovieEntities[0]
            }
            When("load is triggered") {
                runBlocking { result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Success }
            }
            Then("success with end of pagination not reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(false) }
                coVerify(exactly = 0) { serviceMock.loadMovies(any(), any(), any()) }
            }
        }
        Scenario("without next page") {
            Given("no next page") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.anchorPosition } returns null
            }
            When("load is triggered") {
                runBlocking { testedClass.load(LoadType.REFRESH, pagingStateMock) }
            }
            Then("trigger initial load movies from network") {
                coVerify { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) }
            }
        }
        Scenario("with empty list") {
            var result: MediatorResult.Success? = null
            Given("empty list of movies") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.anchorPosition } returns null
                coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } returns
                        moviesPage(emptyList())
            }
            When("load movies returns") {
                runBlocking { result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Success }
            }
            Then("success with end of pagination reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(true) }
            }
        }
        Scenario("with list") {
            var result: MediatorResult.Success? = null
            Given("list of movies") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.anchorPosition } returns null
                coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } returns
                        moviesPage(firstPageMovies)
            }
            When("load movies returns") {
                runBlocking { result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Success }
            }
            Then("do clear movies from database") {
                coVerify { moviesDaoMock.clear() }
            }
            Then("insert movies into database") {
                coVerify { moviesDaoMock.insert(firstPageMovieEntities) }
            }
            Then("success with end of pagination not reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(false) }
            }
        }
    }

    Feature("Movies remote mediator for append") {
        val pagingStateMock = mockk<PagingState<Int, MovieEntity>>()
        val moviesDaoMock = mockk<MoviesDao>()

        val serviceMock = mockk<MoviesService>()
        val databaseMock = mockk<MoviesDatabase>()
        val testedClass by memoized {
            MoviesRemoteMediator(serviceMock, databaseMock, MOVIE_YEAR, MOVIE_MONTH)
        }
        Scenario("with or without next page") {
            Given("append") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.pages } returns listOf(firstPage)
            }
            When("load is triggered") {
                runBlocking { testedClass.load(LoadType.APPEND, pagingStateMock) }
            }
            Then("trigger load movies from network") {
                coVerify { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) }
            }
        }
        Scenario("with empty list") {
            var result: MediatorResult.Success? = null
            Given("empty list of movies") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.pages } returns listOf(firstPage)
                coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns moviesPage(emptyList())
            }
            When("load movies returns") {
                runBlocking { result = testedClass.load(LoadType.APPEND, pagingStateMock) as MediatorResult.Success }
            }
            Then("success with end of pagination reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(true) }
            }
        }
        Scenario("with list") {
            var result: MediatorResult.Success? = null
            Given("list of movies") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.pages } returns listOf(firstPage)
                coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns moviesPage(secondPageMovies)
            }
            When("load movies returns") {
                runBlocking { result = testedClass.load(LoadType.APPEND, pagingStateMock) as MediatorResult.Success }
            }
            Then("do not clear movies from database") {
                coVerify(exactly = 0) { moviesDaoMock.clear() }
            }
            Then("insert movies into database") {
                coVerify { moviesDaoMock.insert(secondPageMovieEntities) }
            }
            Then("success with end of pagination not reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(false) }
            }
        }
    }

    Feature("Movies remote mediator for prepend") {
        val pagingStateMock = mockk<PagingState<Int, MovieEntity>>()
        val moviesDaoMock = mockk<MoviesDao>()

        val serviceMock = mockk<MoviesService>()
        val databaseMock = mockk<MoviesDatabase>()
        val testedClass by memoized {
            MoviesRemoteMediator(serviceMock, databaseMock, MOVIE_YEAR, MOVIE_MONTH)
        }
        Scenario("without previous page") {
            var result: MediatorResult.Success? = null
            Given("no previous page") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.pages } returns emptyList()
            }
            When("load is triggered") {
                runBlocking { result = testedClass.load(LoadType.PREPEND, pagingStateMock) as MediatorResult.Success }
            }
            Then("success with end of pagination reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(true) }
                coVerify(exactly = 0) { serviceMock.loadMovies(any(), any(), any()) }
            }
        }
        Scenario("with previous page") {
            Given("previous page") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.pages } returns listOf(secondPage)
            }
            When("load is triggered") {
                runBlocking { testedClass.load(LoadType.PREPEND, pagingStateMock) }
            }
            Then("trigger load movies from network") {
                coVerify { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) }
            }
        }
        Scenario("with empty list") {
            var result: MediatorResult.Success? = null
            Given("empty list of movies") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.pages } returns listOf(secondPage)
                coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) } returns moviesPage(emptyList())
            }
            When("load movies returns") {
                runBlocking { result = testedClass.load(LoadType.PREPEND, pagingStateMock) as MediatorResult.Success }
            }
            Then("success with end of pagination reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(true) }
            }
        }
        Scenario("with list") {
            var result: MediatorResult.Success? = null
            Given("list of movies") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.pages } returns listOf(secondPage)
                coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, FIRST_PAGE) } returns moviesPage(firstPageMovies)
            }
            When("load movies returns") {
                runBlocking { result = testedClass.load(LoadType.PREPEND, pagingStateMock) as MediatorResult.Success }
            }
            Then("do not clear movies from database") {
                coVerify(exactly = 0) { moviesDaoMock.clear() }
            }
            Then("insert movies into database") {
                coVerify { moviesDaoMock.insert(firstPageMovieEntities) }
            }
            Then("success with end of pagination not reached") {
                expect { that(result?.endOfPaginationReached).isEqualTo(false) }
            }
        }
    }

    Feature("Movies remote mediator for error") {
        val pagingStateMock = mockk<PagingState<Int, MovieEntity>>()
        val moviesDaoMock = mockk<MoviesDao>()

        val serviceMock = mockk<MoviesService>()
        val databaseMock = mockk<MoviesDatabase>()
        val testedClass by memoized {
            MoviesRemoteMediator(serviceMock, databaseMock, MOVIE_YEAR, MOVIE_MONTH)
        }
        Scenario("with exception") {
            val exception = NetworkException(Exception())
            var result: MediatorResult.Error? = null
            Given("exception") {
                setupDatabase(databaseMock, moviesDaoMock)
                every { pagingStateMock.anchorPosition } returns null
                coEvery { serviceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, MOVIES_STARTING_PAGE) } throws exception
            }
            When("load is triggered") {
                runBlocking { result = testedClass.load(LoadType.REFRESH, pagingStateMock) as MediatorResult.Error }
            }
            Then("error with exception") {
                expect { that(result?.throwable).isEqualTo(exception) }
            }
        }
    }

}) {

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

        private const val FIRST_PAGE = 1
        private const val SECOND_PAGE = 2
        private const val THIRD_PAGE = 3

    }

}
