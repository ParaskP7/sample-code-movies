package io.petros.movies.presentation.feature.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class MoviesActivityViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val previousMoviesResultPage = provideMoviesResultPage(NEXT_PAGE, listOf(provideMovie(), provideMovie()))
    private val moviesResultPage = provideMoviesResultPage()

    private lateinit var testedClass: MoviesActivityViewModel
    private val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()
    private val statusObservableMock = mockk<Observer<AdapterStatus>>()
    private val moviesResultPageObservableMock = mockk<Observer<PaginationData<Movie>>>()

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
        every { statusObservableMock.onChanged(any()) } just Runs
        every { moviesResultPageObservableMock.onChanged(any()) } just Runs
    }

    @Test
    fun `Given empty pagination data, when load movies or restore, then a loading status is posted`() {
        testedClass.paginationData.clear()
        expect { that(testedClass.paginationData.isEmpty()).isTrue() }
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage

        testedClass.loadMoviesOrRestore()

        coVerify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
    }

    @Test
    fun `Given empty pagination data, when load movies or restore, then load movies is triggered`() {
        testedClass.paginationData.clear()
        expect { that(testedClass.paginationData.isEmpty()).isTrue() }
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage

        testedClass.loadMoviesOrRestore()

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(null, null, null)) }
    }

    @Test
    fun `Given pagination data, when load movies or restore is triggered, then restore is triggered`() {
        testedClass.paginationData.addPage(previousMoviesResultPage)
        expect { that(testedClass.paginationData.isEmpty()).isFalse() }

        testedClass.loadMoviesOrRestore()

        verify { moviesResultPageObservableMock.onChanged(testedClass.paginationData) }
    }

    @Test
    fun `When load movies is triggered, then a loading status is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage

        testedClass.loadMovies()

        verify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
    }

    @Test
    fun `When load movies is triggered, then load movies use case executes`() {
        coEvery {
            loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(null, null, null))
        } returns moviesResultPage

        testedClass.loadMovies()

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(null, null, null)) }
    }

    @Test
    fun `Given a movie date, when load movies, then load movies use case executes for that date`() {
        coEvery {
            loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE))
        } returns moviesResultPage

        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)) }
    }

    @Test
    fun `When reload movies, then existing pagination data gets cleared before triggering new load`() {
        testedClass.paginationData.addPage(previousMoviesResultPage)
        expect { that(testedClass.paginationData.items().size).isEqualTo(previousMoviesResultPage.movies.size) }
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage

        testedClass.reloadMovies(MOVIE_YEAR, MOVIE_MONTH)

        expect { that(testedClass.paginationData.items().size).isEqualTo(moviesResultPage.movies.size) }
    }

    @Test
    fun `When load movies succeeds, then an idle status is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage

        testedClass.loadMovies()

        verify { statusObservableMock.onChanged(AdapterStatus.IDLE) }
    }

    @Test
    fun `When load movies succeeds, then the updated movies pagination data is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesResultPage

        testedClass.loadMovies()

        verify { moviesResultPageObservableMock.onChanged(testedClass.paginationData.addPage(moviesResultPage)) }
    }

    @Test
    fun `When load movies fails, then an error status is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } answers { throw LoadMoviesUseCase.Error(Exception()) }

        testedClass.loadMovies()

        coVerify { statusObservableMock.onChanged(AdapterStatus.ERROR) }
    }

    @Test
    fun `When load movies fails, then a null movies pagination data is not posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } answers { throw LoadMoviesUseCase.Error(Exception()) }

        testedClass.loadMovies()

        coVerify { moviesResultPageObservableMock.onChanged(null) }
    }

}
