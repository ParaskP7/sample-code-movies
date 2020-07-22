package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadDateUseCaseSpek : CoroutineSpek({

    val date = Result.Success(Pair(MOVIE_YEAR, MOVIE_MONTH))

    val moviesRepositoryMock = mockk<MoviesRepository>()

    Feature("Load movies use case") {
        val testedClass by memoized { LoadDateUseCase(moviesRepositoryMock) }
        Scenario("invoke") {
            var result: Result<Pair<Int?, Int?>>? = null
            Given("movies page stream") {
                coEvery { moviesRepositoryMock.loadDate() } returns date
            }
            When("invoking the use case") {
                result = runBlocking { testedClass() }
            }
            Then("the date is the expected one") {
                expect { that(result).isEqualTo(date) }
            }
        }
    }

}) {

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
