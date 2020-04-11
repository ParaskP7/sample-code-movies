package io.petros.movies.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.utils.exhaustive
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesViewModel constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase
) : ViewModel() {

    val statusObservable = MutableLiveData<AdapterStatus>()
    val moviesObservable = MutableLiveData<PaginationData<Movie>>()

    val paginationData = PaginationData<Movie>()

    fun loadMoviesOrRestore(year: Int? = null, month: Int? = null) {
        if (paginationData.isEmpty()) loadMovies(year, month) else moviesObservable.postValue(paginationData)
    }

    fun loadMovies(year: Int? = null, month: Int? = null, page: Int? = null) = viewModelScope.launch {
        statusObservable.postValue(AdapterStatus.LOADING)
        when (val movies = loadMoviesUseCase.execute(LoadMoviesUseCase.Params(year, month, page))) {
            is Result.Success -> onLoadMoviesSuccess(movies.value)
            is Result.Error -> onLoadMoviesError(movies.cause)
        }.exhaustive
    }

    private fun onLoadMoviesSuccess(movies: MoviesPage) {
        Timber.d("Load movies success. [Movies: $movies]")
        statusObservable.postValue(AdapterStatus.IDLE)
        moviesObservable.postValue(paginationData.addPage(movies))
    }

    private fun onLoadMoviesError(error: Exception) {
        Timber.w(error, "Load movies error.")
        statusObservable.postValue(AdapterStatus.ERROR)
        moviesObservable.postValue(null)
    }

    fun reloadMovies(year: Int? = null, month: Int? = null) {
        paginationData.clear()
        loadMovies(year, month)
    }

}
