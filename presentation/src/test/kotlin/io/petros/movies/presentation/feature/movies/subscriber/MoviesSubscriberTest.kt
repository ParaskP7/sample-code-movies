package io.petros.movies.presentation.feature.movies.subscriber

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMoviesResultPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesSubscriberTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val moviesResultPage = provideMoviesResultPage()

    private lateinit var testedClass: MoviesSubscriber
    private val statusObservableMock = mock<Observer<AdapterStatus>>()
    private val moviesResultPageObservableMock = mock<Observer<PaginationData<Movie>>>()

    @Before
    fun setUp() {
        testedClass = MoviesSubscriber(MutableLiveData(), MutableLiveData(), PaginationData())
        testedClass.statusObservable.observeForever(statusObservableMock)
        testedClass.moviesObservable.observeForever(moviesResultPageObservableMock)
    }

    @Test
    fun `When load movies succeeds, then an idle status is posted`() {
        testedClass.onSuccess(moviesResultPage)

        verify(statusObservableMock).onChanged(AdapterStatus.IDLE)
    }

    @Test
    fun `When load movies succeeds, then the updated movies pagination data is posted`() {
        testedClass.onSuccess(moviesResultPage)

        verify(moviesResultPageObservableMock).onChanged(testedClass.paginationData.addPage(moviesResultPage))
    }

    @Test
    fun `When load movies fails, then an error status is posted`() {
        testedClass.onError(Exception())

        verify(statusObservableMock).onChanged(AdapterStatus.ERROR)
    }

    @Test
    fun `When load movies fails, then a null movies pagination data is not posted`() {
        testedClass.onError(Exception())

        verify(moviesResultPageObservableMock).onChanged(null)
    }

}
