package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.repository.movie.MoviesRepository
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

private val movie = Result.Success(movie())
private val movieStream = flow<Result<Movie>> { movie }

@ExperimentalCoroutinesApi
class LoadMovieUseCaseSpek : CoroutineSpek({

    val moviesRepositoryMock = mockk<MoviesRepository>()

    Feature("Load movies use case") {
        val testedClass by memoized { LoadMovieUseCase(moviesRepositoryMock) }
        Scenario("execute") {
            var result: Flow<Result<Movie>>? = null
            Given("movie stream") {
                coEvery { moviesRepositoryMock.loadMovieStream(MOVIE_ID) } returns movieStream
            }
            When("executing the use case") {
                result = runBlocking { testedClass(LoadMovieUseCase.Params(MOVIE_ID)) }
            }
            Then("the movie is the expected one") {
                runBlocking { result?.collectLatest { expect { that(it).isEqualTo(movie) } } }
            }
        }
    }

}) {

    companion object {

        private const val MOVIE_ID = 419_704

    }

}
