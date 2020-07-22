package io.petros.movies.domain.interactor.movie

import io.mockk.coEvery
import io.mockk.mockk
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class LoadDateUseCaseTest {

    companion object {

        private const val MOVIE_YEAR = 2018
        private const val MOVIE_MONTH = 7

    }

    @get:Rule val coroutineScope = MainCoroutineScopeRule()

    private val date = Result.Success(Pair(MOVIE_YEAR, MOVIE_MONTH))

    private val moviesRepositoryMock = mockk<MoviesRepository>()
    private val testedClass = LoadDateUseCase(moviesRepositoryMock)

    @Test
    fun `when invoking the use case, then the date is the expected one`() = coroutineScope.runBlockingTest {
        coEvery { moviesRepositoryMock.loadDate() } returns date

        val result = testedClass()

        expect { that(result).isEqualTo(date) }
    }

}
