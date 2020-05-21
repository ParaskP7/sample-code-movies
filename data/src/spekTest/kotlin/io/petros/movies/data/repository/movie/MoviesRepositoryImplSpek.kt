package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.network.WebService
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class MoviesRepositoryImplSpek : CoroutineSpek({

    val moviesPage = Result.Success(moviesPage())

    val webServiceMock = mockk<WebService>()

    Feature("Movies repository") {
        val testedClass by memoized { MoviesRepositoryImpl(webServiceMock) }
        Scenario("loading movies") {
            var result: Result<MoviesPage>? = null
            Given("movies page response") {
                coEvery { webServiceMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) } returns moviesPage.value
            }
            When("load movies is triggered") {
                runBlocking { result = testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, SECOND_PAGE) }
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
