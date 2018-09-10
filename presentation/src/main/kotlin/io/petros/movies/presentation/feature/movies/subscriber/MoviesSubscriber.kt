package io.petros.movies.presentation.feature.movies.subscriber

import android.arch.lifecycle.MutableLiveData
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

class MoviesSubscriber(
    private val moviesObservable: MutableLiveData<MoviesResultPage>
) : DisposableSingleObserver<MoviesResultPage>() {

    override fun onSuccess(movies: MoviesResultPage) {
        Timber.d("Load movies success. [Movies: $movies]")
        moviesObservable.postValue(movies)
    }

    override fun onError(exception: Throwable) {
        Timber.w(exception, "Load movies error.")
    }

}
