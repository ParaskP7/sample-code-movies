package io.petros.movies.presentation.feature.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesActivityViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var testedClass: MoviesActivityViewModel
    private val statusObservableMock = mock<Observer<AdapterStatus>>()
    private val moviesResultPageObservableMock = mock<Observer<PaginationData<Movie>>>()
    private val loadMoviesUseCaseMock = mock<LoadMoviesUseCase>()

    @Before
    fun setUp() {
        testedClass = MoviesActivityViewModel(loadMoviesUseCaseMock)
        testedClass.statusObservable.observeForever(statusObservableMock)
        testedClass.moviesObservable.observeForever(moviesResultPageObservableMock)
    }

    @Test
    fun `Given empty pagination data, when load movies or restore is triggered, then load movies is triggered`() {
        testedClass.paginationData.clear()
        assertThat(testedClass.paginationData.isEmpty()).isTrue()

        testedClass.loadMoviesOrRestore()

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
        verify(loadMoviesUseCaseMock).execute(any(), eq(LoadMoviesUseCase.Params.with(null, null, null)))
    }

    @Test
    fun `Given pagination data, when load movies or restore is triggered, then restore is triggered`() {
        val movies = listOf(provideMovie(id = 1), provideMovie(id = 2), provideMovie(id = 3))
        testedClass.paginationData.addPage(MoviesResultPage(NEXT_PAGE, movies))
        assertThat(testedClass.paginationData.isEmpty()).isFalse()

        testedClass.loadMoviesOrRestore()

        verify(moviesResultPageObservableMock).onChanged(testedClass.paginationData)
    }

    @Test
    fun `When load movies is triggered, then a loading status is posted`() {
        testedClass.loadMovies()

        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
    }

    @Test
    fun `When load movies is triggered, then load movies use case executes`() {
        testedClass.loadMovies()

        verify(loadMoviesUseCaseMock).execute(any(), eq(LoadMoviesUseCase.Params.with(null, null, null)))
    }

    @Test
    fun `Given a movie year, when load movies is triggered, then load movies use case executes for that year`() {
        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        verify(loadMoviesUseCaseMock).execute(any(), eq(LoadMoviesUseCase.Params.with(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)))
    }

    @Test
    fun `When reload movies is triggered, then existing pagination data gets cleared before triggering new load`() {
        val movies = listOf(provideMovie(id = 1), provideMovie(id = 2), provideMovie(id = 3))
        testedClass.paginationData.addPage(MoviesResultPage(NEXT_PAGE, movies))
        assertThat(testedClass.paginationData.isEmpty()).isFalse()

        testedClass.reloadMovies(MOVIE_YEAR, MOVIE_MONTH)

        assertThat(testedClass.paginationData.isEmpty()).isTrue()
        verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
        verify(loadMoviesUseCaseMock).execute(any(), eq(LoadMoviesUseCase.Params.with(MOVIE_YEAR, MOVIE_MONTH, null)))
    }

    @Test
    fun `When view model is destroyed, then load movies use case is disposed`() {
        testedClass.onCleared()

        verify(loadMoviesUseCaseMock).dispose()
    }

}
