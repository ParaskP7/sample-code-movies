package io.petros.movies.presentation.feature.movies

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.movies.presentation.feature.movies.subscriber.MoviesSubscriber
import javax.inject.Inject

class MoviesActivityViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase
) : ViewModel() {

    val statusObservable = MutableLiveData<AdapterStatus>()
    val moviesObservable = MutableLiveData<PaginationData<Movie>>()

    val paginationData = PaginationData<Movie>()

    fun loadMoviesOrRestore(year: Int? = null, month: Int? = null) {
        if (paginationData.isEmpty()) loadMovies(year, month) else moviesObservable.postValue(paginationData)
    }

    fun loadMovies(year: Int? = null, month: Int? = null, page: Int? = null) {
        statusObservable.postValue(AdapterStatus.LOADING)
        loadMoviesUseCase.execute(
            MoviesSubscriber(statusObservable, moviesObservable, paginationData),
            LoadMoviesUseCase.Params.with(year, month, page)
        )
    }

    fun reloadMovies(year: Int? = null, month: Int? = null) {
        paginationData.clear()
        loadMovies(year, month)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public override fun onCleared() {
        super.onCleared()
        loadMoviesUseCase.dispose()
    }

}
