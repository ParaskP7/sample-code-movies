package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.database.MoviesDatabase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.network.MoviesService
import io.petros.movies.test.domain.movie
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class MoviesRepositoryImplSpek : CoroutineSpek({

    val movie = Result.Success(movie())

    val serviceMock = mockk<MoviesService>()
    val moviesDatabaseMock = mockk<MoviesDatabase>()

    Feature("Movies repository") {
        val testedClass by memoized { MoviesRepositoryImpl(serviceMock, moviesDatabaseMock) }
        Scenario("loading movie") {
            var result: Result<Movie>? = null
            Given("movie response") {
                coEvery { serviceMock.loadMovie(MOVIE_ID) } returns movie.value
            }
            When("load movie is triggered") {
                runBlocking { result = testedClass.loadMovie(MOVIE_ID) }
            }
            Then("the movie is the expected one") {
                expect { that(result).isEqualTo(movie) }
            }
        }
    }

}) {

    companion object {

        private const val MOVIE_ID = 419_704

    }

}
