package io.petros.movies.presentation.feature.movies

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import org.junit.Before
import org.junit.Test

class MoviesActivityViewModelTest {

    private lateinit var testedClass: MoviesActivityViewModel
    private val loadMoviesUseCaseMock = mock<LoadMoviesUseCase>()

    @Before
    fun setUp() {
        testedClass = MoviesActivityViewModel(loadMoviesUseCaseMock)
    }

    @Test
    fun `When load movies is triggered, then load movies use case executes`() {
        testedClass.loadMovies()

        verify(loadMoviesUseCaseMock).execute(any(), eq(null))
    }

    @Test
    fun `When view model is destroyed, then load movies use case is disposed`() {
        testedClass.onCleared()

        verify(loadMoviesUseCaseMock).dispose()
    }

}
