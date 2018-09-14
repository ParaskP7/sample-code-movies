package io.petros.movies.presentation.feature.movies.subscriber

import android.arch.lifecycle.MutableLiveData
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class MoviesSubscriber(
    val statusObservable: MutableLiveData<AdapterStatus>,
    val moviesObservable: MutableLiveData<PaginationData<Movie>>,
    val paginationData: PaginationData<Movie>
) : DisposableSingleObserver<MoviesResultPage>() {

    override fun onSuccess(movies: MoviesResultPage) {
        Timber.d("Load movies success. [Movies: $movies]")
        statusObservable.postValue(AdapterStatus.IDLE)
        moviesObservable.postValue(paginationData.addPage(movies))
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Load movies error.")
        statusObservable.postValue(AdapterStatus.ERROR)
        moviesObservable.postValue(null)
    }

}
