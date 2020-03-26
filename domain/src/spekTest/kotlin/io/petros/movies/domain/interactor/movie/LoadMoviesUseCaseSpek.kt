package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase.Params
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.moviesResultPage
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
object LoadMoviesUseCaseSpek : CoroutineSpek({

    val moviesResultPage = moviesResultPage()
    val moviesRepositoryMock = mockk<MoviesRepository>()
    coEvery { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) } returns moviesResultPage

    Feature("Load movies use case") {
        val testedClass by memoized { LoadMoviesUseCase(moviesRepositoryMock) }
        Scenario("execute") {
            var result: MoviesResultPage? = null
            When("executing the use case") {
                result = runBlocking { testedClass.execute(Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)) }
            }
            Then("the repository triggers load movies") {
                coVerify { moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE) }
            }
            Then("the movies result page is the expected one") {
                expect { that(result).isEqualTo(moviesResultPage) }
            }
        }
    }

})
