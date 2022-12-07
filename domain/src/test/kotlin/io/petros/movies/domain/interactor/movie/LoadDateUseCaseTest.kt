package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.repository.movie.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadDateUseCaseTest {

    private val date = Result.Success(MOVIE_YEAR to MOVIE_MONTH)

    private val moviesRepositoryMock = mockk<MoviesRepository>()
    private val testedClass = LoadDateUseCase(moviesRepositoryMock)

    @Test
    fun `when invoking the use case, then the date is the expected one`() = runTest {
        coEvery { moviesRepositoryMock.loadDate() } returns date

        val result = testedClass()

        expect { that(result).isEqualTo(date) }
    }

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

}
