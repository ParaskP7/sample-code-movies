package io.petros.movies.presentation.feature.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
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

    fun loadMoviesOrRestore(year: Int? = null) {
        if (paginationData.isEmpty()) loadMovies(year) else moviesObservable.postValue(paginationData)
    }

    fun loadMovies(year: Int? = null, page: Int? = null) {
        statusObservable.postValue(AdapterStatus.LOADING)
        loadMoviesUseCase.execute(
            MoviesSubscriber(statusObservable, moviesObservable, paginationData),
            LoadMoviesUseCase.Params.with(year, page)
        )
    }

    fun reloadMovies(year: Int? = null) {
        paginationData.clear()
        loadMovies(year)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public override fun onCleared() {
        super.onCleared()
        loadMoviesUseCase.dispose()
    }

}
