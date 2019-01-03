package io.petros.movies.presentation.feature.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_MONTH
import io.petros.movies.test.domain.TestMoviesProvider.Companion.MOVIE_YEAR
import io.petros.movies.test.domain.TestMoviesProvider.Companion.NEXT_PAGE
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMovie
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMoviesResultPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesActivityViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val previousMoviesResultPage = provideMoviesResultPage(NEXT_PAGE, listOf(provideMovie(), provideMovie()))
    private val moviesResultPage = provideMoviesResultPage()

    private lateinit var testedClass: MoviesActivityViewModel
    private val loadMoviesUseCaseMock = mock<LoadMoviesUseCase>()
    private val statusObservableMock = mock<Observer<AdapterStatus>>()
    private val moviesResultPageObservableMock = mock<Observer<PaginationData<Movie>>>()

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        testedClass = MoviesActivityViewModel(loadMoviesUseCaseMock)
        testedClass.uiScope = CoroutineScope(Dispatchers.Unconfined)
        testedClass.statusObservable.observeForever(statusObservableMock)
        testedClass.moviesObservable.observeForever(moviesResultPageObservableMock)
        setUpMocks()
    }

    private fun setUpMocks() {
        runBlocking {
            whenever(loadMoviesUseCaseMock.execute(any())).thenReturn(moviesResultPage)
        }
    }

    @Test
    fun `Given empty pagination data, when load movies or restore is triggered, then load movies is triggered`() {
        runBlocking {
            testedClass.paginationData.clear()
            assertThat(testedClass.paginationData.isEmpty()).isTrue()

            testedClass.loadMoviesOrRestore()

            verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
            verify(loadMoviesUseCaseMock).execute(LoadMoviesUseCase.Params(null, null, null))
        }
    }

    @Test
    fun `Given pagination data, when load movies or restore is triggered, then restore is triggered`() {
        testedClass.paginationData.addPage(previousMoviesResultPage)
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
        runBlocking {
            testedClass.loadMovies()

            verify(loadMoviesUseCaseMock).execute(LoadMoviesUseCase.Params(null, null, null))
        }
    }

    @Test
    fun `Given a movie date, when load movies is triggered, then load movies use case executes for that date`() {
        runBlocking {
            testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

            verify(loadMoviesUseCaseMock).execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE))
        }
    }

    @Test
    fun `When reload movies is triggered, then existing pagination data gets cleared before triggering new load`() {
        runBlocking {
            testedClass.paginationData.addPage(previousMoviesResultPage)
            assertThat(testedClass.paginationData.allPageItems.size).isEqualTo(previousMoviesResultPage.movies.size)

            testedClass.reloadMovies(MOVIE_YEAR, MOVIE_MONTH)

            assertThat(testedClass.paginationData.allPageItems.size).isEqualTo(moviesResultPage.movies.size)
            verify(statusObservableMock).onChanged(AdapterStatus.LOADING)
            verify(loadMoviesUseCaseMock).execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null))
        }
    }

    @Test
    fun `When load movies succeeds, then an idle status is posted`() {
        testedClass.loadMovies()

        verify(statusObservableMock).onChanged(AdapterStatus.IDLE)
    }

    @Test
    fun `When load movies succeeds, then the updated movies pagination data is posted`() {
        testedClass.loadMovies()

        verify(moviesResultPageObservableMock).onChanged(testedClass.paginationData.addPage(moviesResultPage))
    }

    @Test
    fun `When load movies fails, then an error status is posted`() {
        runBlocking {
            doAnswer { throw LoadMoviesUseCase.Error(Exception()) }.whenever(loadMoviesUseCaseMock).execute(any())

            testedClass.loadMovies()

            verify(statusObservableMock).onChanged(AdapterStatus.ERROR)
        }
    }

    @Test
    fun `When load movies fails, then a null movies pagination data is not posted`() {
        runBlocking {
            doAnswer { throw LoadMoviesUseCase.Error(Exception()) }.whenever(loadMoviesUseCaseMock).execute(any())

            testedClass.loadMovies()

            verify(moviesResultPageObservableMock).onChanged(null)
        }
    }

}
