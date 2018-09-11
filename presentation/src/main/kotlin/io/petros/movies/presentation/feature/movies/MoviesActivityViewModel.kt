package io.petros.movies.presentation.feature.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.presentation.feature.movies.subscriber.MoviesSubscriber
import javax.inject.Inject

class MoviesActivityViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase
) : ViewModel() {

    val moviesObservable = MutableLiveData<MoviesResultPage>()

    fun loadMovies() {
        loadMoviesUseCase.execute(MoviesSubscriber(moviesObservable))
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public override fun onCleared() {
        super.onCleared()
        loadMoviesUseCase.dispose()
    }

}
