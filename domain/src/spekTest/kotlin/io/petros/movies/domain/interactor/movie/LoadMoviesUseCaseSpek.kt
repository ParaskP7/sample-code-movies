package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadMoviesUseCaseSpek : CoroutineSpek({

    val params = LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE)

    val moviesPage = Result.Success(moviesPage())

    val moviesRepositoryMock = mockk<MoviesRepository>()

    Feature("Load movies use case") {
        val testedClass by memoized { LoadMoviesUseCase(moviesRepositoryMock) }
        Scenario("execute") {
            var result: Result<MoviesPage>? = null
            Given("movies page") {
                coEvery { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns moviesPage
            }
            When("executing the use case") {
                result = runBlocking { testedClass.execute(params) }
            }
            Then("the movies page is the expected one") {
                expect { that(result).isEqualTo(moviesPage) }
            }
        }
    }

}) {

    companion object {

        private const val SECOND_PAGE = 2
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
