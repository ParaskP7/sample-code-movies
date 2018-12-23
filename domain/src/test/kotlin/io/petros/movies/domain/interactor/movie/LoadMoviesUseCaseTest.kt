package io.petros.movies.domain.interactor.movie

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMoviesResultPage
import io.petros.movies.test.rx.TestRxSchedulersProvider.Companion.provideRxSchedulers
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class LoadMoviesUseCaseTest {

    private val params = LoadMoviesUseCase.Params.with(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

    private val moviesResultPage = provideMoviesResultPage()

    private lateinit var testedClass: LoadMoviesUseCase
    private val moviesRepositoryMock = mock<MoviesRepository>()

    @Before
    fun setUp() {
        testedClass = LoadMoviesUseCase(moviesRepositoryMock, provideRxSchedulers())
    }

    @Test
    fun `When load movies use case is build, then movies repository triggers load movies`() {
        testedClass.buildUseCaseObservable(params)

        verify(moviesRepositoryMock).loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)
    }

    @Test
    fun `When load movies returns, then the movies result page is the expected one`() {
        whenever(moviesRepositoryMock.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE))
            .thenReturn(Single.just(moviesResultPage))

        val result = testedClass.buildUseCaseObservable(params).blockingGet()

        assertThat(result).isEqualTo(moviesResultPage)
    }

}
