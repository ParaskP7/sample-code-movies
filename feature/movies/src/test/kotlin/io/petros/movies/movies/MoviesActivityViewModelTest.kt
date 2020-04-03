package io.petros.movies.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.NetworkError
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.test.domain.MOVIE_MONTH
import io.petros.movies.test.domain.MOVIE_YEAR
import io.petros.movies.test.domain.NEXT_PAGE
import io.petros.movies.test.domain.movie
import io.petros.movies.test.domain.moviesPage
import io.petros.movies.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue

@ExperimentalCoroutinesApi
class MoviesActivityViewModelTest {

    @get:Rule val coroutineScope = MainCoroutineScopeRule()
    @get:Rule val rule = InstantTaskExecutorRule()

    private val previousMoviesPage = moviesPage(NEXT_PAGE, listOf(movie(), movie()))
    private val moviesPage = Result.Success(moviesPage())

    @Suppress("LateinitUsage") private lateinit var testedClass: MoviesActivityViewModel
    private val loadMoviesUseCaseMock = mockk<LoadMoviesUseCase>()
    private val statusObservableMock = mockk<Observer<AdapterStatus>>()
    private val moviesPageObservableMock = mockk<Observer<PaginationData<Movie>>>()

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        testedClass = MoviesActivityViewModel(loadMoviesUseCaseMock)
        testedClass.statusObservable.observeForever(statusObservableMock)
        testedClass.moviesObservable.observeForever(moviesPageObservableMock)
    }

    @Test
    fun `when loading movies, then a loading status is posted`() {
        testedClass.loadMovies()

        verify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
    }

    @Test
    fun `when loading movies, then the load movies use case executes`() {
        testedClass.loadMovies(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, NEXT_PAGE)) }
    }

    @Test
    fun `when loading movies succeeds, then an idle status is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage

        testedClass.loadMovies()

        verify { statusObservableMock.onChanged(AdapterStatus.IDLE) }
    }

    @Test
    fun `when load movies succeeds, then a page is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage

        testedClass.loadMovies()

        verify { moviesPageObservableMock.onChanged(testedClass.paginationData.addPage(moviesPage.value)) }
    }

    @Test
    fun `when load movies fails, then an error status is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.loadMovies()

        coVerify { statusObservableMock.onChanged(AdapterStatus.ERROR) }
    }

    @Test
    fun `when load movies fails, then a null page is posted`() {
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns NetworkError(Exception())

        testedClass.loadMovies()

        coVerify { moviesPageObservableMock.onChanged(null) }
    }

    @Test
    fun `given no page, when loading movies or restoring, then a loading status is posted`() {
        testedClass.paginationData.clear()
        expect { that(testedClass.paginationData.isEmpty()).isTrue() }

        testedClass.loadMoviesOrRestore()

        coVerify { statusObservableMock.onChanged(AdapterStatus.LOADING) }
    }

    @Test
    fun `given no page, when loading movies or restoring, then the load movies use case executes`() {
        testedClass.paginationData.clear()
        expect { that(testedClass.paginationData.isEmpty()).isTrue() }

        testedClass.loadMoviesOrRestore()

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(null, null, null)) }
    }

    @Test
    fun `given a page, when loading movies or restoring, then restore is triggered`() {
        testedClass.paginationData.addPage(previousMoviesPage)
        expect { that(testedClass.paginationData.isEmpty()).isFalse() }

        testedClass.loadMoviesOrRestore()

        verify { moviesPageObservableMock.onChanged(testedClass.paginationData) }
    }

    @Test
    fun `given a page, when reloading movies, then existing data gets cleared`() {
        testedClass.paginationData.addPage(previousMoviesPage)
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage

        testedClass.reloadMovies(MOVIE_YEAR, MOVIE_MONTH)

        expect { that(testedClass.paginationData.items().size).isEqualTo(moviesPage.value.movies.size) }
    }

    @Test
    fun `given a page, when reloading movies, then a new load of movies is triggered`() {
        testedClass.paginationData.addPage(previousMoviesPage)
        coEvery { loadMoviesUseCaseMock.execute(any()) } returns moviesPage

        testedClass.reloadMovies(MOVIE_YEAR, MOVIE_MONTH)

        coVerify { loadMoviesUseCaseMock.execute(LoadMoviesUseCase.Params(MOVIE_YEAR, MOVIE_MONTH, null)) }
    }

}
