package io.petros.movies.movies

import androidx.lifecycle.viewModelScope
import io.petros.movies.core.view_model.MviViewModel
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.utils.exhaustive
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase
) : MviViewModel<MoviesIntent, MoviesState, MoviesSideEffect>() {

    init {
        state = MoviesReducer.init()
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun process(intent: MoviesIntent) {
        super.process(intent)
        when (intent) {
            is MoviesIntent.IdleMovies -> idleMovies(intent.year, intent.month)
            is MoviesIntent.LoadMovies -> loadMovies(intent.year, intent.month, intent.page)
            is MoviesIntent.ReloadMovies -> reloadMovies(intent.year, intent.month)
        }.exhaustive
    }

    private fun idleMovies(year: Int? = null, month: Int? = null) {
        state = MoviesReducer.reduce(state, MoviesAction.Idle(year, month))
    }

    private fun loadMovies(year: Int? = null, month: Int? = null, page: Int? = null) = viewModelScope.launch {
        state = MoviesReducer.reduce(state, MoviesAction.Load(year, month))
        when (val movies = loadMoviesUseCase.execute(LoadMoviesUseCase.Params(year, month, page))) {
            is Result.Success -> onLoadMoviesSuccess(movies.value)
            is Result.Error -> onLoadMoviesError(movies.cause)
        }.exhaustive
    }

    private fun onLoadMoviesSuccess(movies: MoviesPage) {
        Timber.d("Load movies success. [Movies: $movies]")
        state = MoviesReducer.reduce(state, MoviesAction.Success(movies))
    }

    private fun onLoadMoviesError(error: Exception) {
        Timber.w(error, "Load movies error.")
        state = MoviesReducer.reduce(state, MoviesAction.Error)
        sideEffect = MoviesReducer.once(MoviesAction.Error)
    }

    private fun reloadMovies(year: Int? = null, month: Int? = null) {
        state = MoviesReducer.reduce(state, MoviesAction.Reload(year, month))
        loadMovies(year, month)
    }

}
