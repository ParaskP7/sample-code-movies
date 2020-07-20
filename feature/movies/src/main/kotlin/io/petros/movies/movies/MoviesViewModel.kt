package io.petros.movies.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.LoadType
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.petros.movies.core.view_model.MviViewModel
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.utils.exhaustive
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase,
) : MviViewModel<MoviesIntent, MoviesState, MoviesSideEffect>() {

    init {
        state = MoviesReducer.init()
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun process(intent: MoviesIntent) {
        super.process(intent)
        when (intent) {
            is MoviesIntent.LoadMovies -> loadMovies(doLoadMovies(), intent)
            is MoviesIntent.ErrorMovies -> onLoadMoviesError(intent.error, intent.loadType)
            is MoviesIntent.ReloadMovies -> reloadMovies(intent.year, intent.month)
        }.exhaustive
    }

    private fun doLoadMovies() = state.movies == PagingData.empty<Movie>()

    private fun loadMovies(doLoadLoad: Boolean, intent: MoviesIntent.LoadMovies) {
        if (doLoadLoad) {
            loadMovies(intent.year, intent.month)
        } else {
            idleMovies(intent.year, intent.month)
        }
    }

    private fun loadMovies(year: Int? = null, month: Int? = null) = viewModelScope.launch {
        loadMoviesUseCase(LoadMoviesUseCase.Params(year, month, null))
            .cachedIn(viewModelScope)
            .collectLatest { onLoadMoviesSuccess(it) }
    }

    private fun onLoadMoviesSuccess(movies: PagingData<Movie>) {
        Timber.d("Load movies success. [Movies: $movies]")
        state = MoviesReducer.reduce(state, MoviesAction.Success(movies))
    }

    private fun onLoadMoviesError(error: Throwable, loadType: LoadType) {
        Timber.w(error, "Load movies error.")
        state = MoviesReducer.reduce(state, MoviesAction.Error(loadType))
        sideEffect = MoviesReducer.once(MoviesAction.Error(loadType))
    }

    private fun idleMovies(year: Int? = null, month: Int? = null) {
        state = MoviesReducer.reduce(state, MoviesAction.Idle(year, month))
    }

    private fun reloadMovies(year: Int? = null, month: Int? = null) {
        state = MoviesReducer.reduce(state, MoviesAction.Reload(year, month))
        loadMovies(year, month)
    }

}
