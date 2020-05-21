package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.movie
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadMovieUseCaseSpek : CoroutineSpek({

    val movie = Result.Success(movie())
    val moviesRepositoryMock = mockk<MoviesRepository>()
    coEvery { moviesRepositoryMock.loadMovie(MOVIE_ID) } returns movie

    Feature("Load movies use case") {
        val testedClass by memoized { LoadMovieUseCase(moviesRepositoryMock) }
        Scenario("execute") {
            var result: Result<Movie>? = null
            When("executing the use case") {
                result = runBlocking { testedClass.execute(LoadMovieUseCase.Params(MOVIE_ID)) }
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
