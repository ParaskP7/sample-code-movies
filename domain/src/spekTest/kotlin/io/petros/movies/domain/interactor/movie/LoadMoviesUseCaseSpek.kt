package io.petros.movies.domain.interactor.movie

import androidx.paging.PagingData
import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.utils.CoroutineSpek
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadMoviesUseCaseSpek : CoroutineSpek({

    val params = LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH)

    val moviesPageStream = mockk<Flow<PagingData<Movie>>>()

    val moviesRepositoryMock = mockk<MoviesRepository>()

    Feature("Load movies use case") {
        val testedClass by memoized { LoadMoviesUseCase(moviesRepositoryMock) }
        Scenario("invoke") {
            var result: Flow<PagingData<Movie>>? = null
            Given("movies page stream") {
                coEvery { moviesRepositoryMock.loadMoviesStream(MOVIE_YEAR, MOVIE_MONTH) } returns moviesPageStream
            }
            When("invoking the use case") {
                result = runBlocking { testedClass(params) }
            }
            Then("the movies page stream is the expected one") {
                expect { that(result).isEqualTo(moviesPageStream) }
            }
        }
    }

}) {

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
