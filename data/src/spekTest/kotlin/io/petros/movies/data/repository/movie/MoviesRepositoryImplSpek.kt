package io.petros.movies.data.repository.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.network.WebService
import io.petros.movies.test.domain.movie
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
    val movie = Result.Success(movie())

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
        Scenario("loading movie") {
            var result: Result<Movie>? = null
            Given("movie response") {
                coEvery { webServiceMock.loadMovie(MOVIE_ID) } returns movie.value
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

        private const val SECOND_PAGE = 2
        private const val MOVIE_ID = 419_704
        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
