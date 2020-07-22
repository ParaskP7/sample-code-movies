package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.petros.movies.database.MoviesDatabase
import io.petros.movies.database.dao.MoviesDao
import io.petros.movies.database.entity.MovieEntity
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.network.MoviesService
import io.petros.movies.test.domain.movie
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
private fun setupDatabase(databaseMock: MoviesDatabase, moviesDaoMock: MoviesDao) {
    every { databaseMock.moviesDao() } returns moviesDaoMock
}

private const val SECOND_PAGE = 2
private const val MOVIE_YEAR = 2018
private const val MOVIE_MONTH = 7

private val date = Result.Success(Pair(MOVIE_YEAR, MOVIE_MONTH))
private val movie = Result.Success(movie())
private val movieEntity = MovieEntity.from(null, SECOND_PAGE, date.value.first, date.value.second, movie.value)
private val movieEntityStream = flow<MovieEntity> { movieEntity }

@ExperimentalCoroutinesApi
class MoviesRepositoryImplSpek : CoroutineSpek({

    val moviesDaoMock = mockk<MoviesDao>()

    val serviceMock = mockk<MoviesService>()
    val databaseMock = mockk<MoviesDatabase>()

    Feature("Movies repository") {
        val testedClass by memoized { MoviesRepositoryImpl(serviceMock, databaseMock) }
        Scenario("loading date") {
            var result: Result<Pair<Int?, Int?>>? = null
            Given("date response") {
                setupDatabase(databaseMock, moviesDaoMock)
                coEvery { moviesDaoMock.firstMovie() } returns movieEntity
            }
            When("load date is triggered") {
                runBlocking { result = testedClass.loadDate() }
            }
            Then("the date is the expected one") {
                expect { that(result).isEqualTo(date) }
            }
        }
        Scenario("loading movie") {
            var result: Flow<Result<Movie>>? = null
            Given("movie response") {
                coEvery { moviesDaoMock.movie(MOVIE_ID) } returns movieEntityStream
            }
            When("load movie is triggered") {
                runBlocking { result = testedClass.loadMovieStream(MOVIE_ID) }
            }
            Then("the movie stream is the expected one") {
                runBlocking { result?.collectLatest { expect { that(it).isEqualTo(movie) } } }
            }
        }
    }

}) {

    companion object {

        private const val MOVIE_ID = 419_704

    }

}
