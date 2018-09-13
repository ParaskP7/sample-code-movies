package io.petros.movies.presentation.feature.movies

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesActivityViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var testedClass: MoviesActivityViewModel
    private val statusObservableMock = mock<Observer<AdapterStatus>>()
    private val loadMoviesUseCaseMock = mock<LoadMoviesUseCase>()

    @Before
    fun setUp() {
        testedClass = MoviesActivityViewModel(loadMoviesUseCaseMock)
        testedClass.statusObservable.observeForever(statusObservableMock)
    }

    @Test
    fun `When load movies is triggered, then a loading status is posted`() {
        testedClass.loadMovies()

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
    }

    @Test
    fun `When load movies is triggered, then load movies use case executes`() {
        testedClass.loadMovies()

        verify(loadMoviesUseCaseMock).execute(any(), eq(LoadMoviesUseCase.Params.with(null)))
    }

    @Test
    fun `Given a movie year, when load movies is triggered, then load movies use case executes for that year`() {
        testedClass.loadMovies(MOVIE_YEAR)

        verify(loadMoviesUseCaseMock).execute(any(), eq(LoadMoviesUseCase.Params.with(MOVIE_YEAR)))
    }

    @Test
    fun `When view model is destroyed, then load movies use case is disposed`() {
        testedClass.onCleared()

        verify(loadMoviesUseCaseMock).dispose()
    }

}
