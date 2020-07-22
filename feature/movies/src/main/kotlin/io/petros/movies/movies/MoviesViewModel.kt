package io.petros.movies.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.LoadType
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.petros.movies.core.view_model.MviViewModel
import io.petros.movies.domain.interactor.movie.LoadDateUseCase
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.utils.exhaustive
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@Suppress("TooManyFunctions")
class MoviesViewModel(
    private val loadDateUseCase: LoadDateUseCase,
    private val loadMoviesUseCase: LoadMoviesUseCase,
) : MviViewModel<MoviesIntent, MoviesState, MoviesSideEffect>() {

    init {
        state = MoviesReducer.init()
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun process(intent: MoviesIntent) {
        super.process(intent)
        when (intent) {
            is MoviesIntent.LoadDate -> loadDate()
            is MoviesIntent.LoadMovies -> loadMovies(doLoadMovies(), intent)
            is MoviesIntent.ErrorMovies -> onLoadMoviesError(intent.error, intent.loadType)
            is MoviesIntent.ReloadMovies -> reloadMovies(intent.year, intent.month)
        }.exhaustive
    }

    /* DATE */

    private fun loadDate() = viewModelScope.launch {
        when (val yearMonth = loadDateUseCase()) {
            is Result.Success -> onLoadDateSuccess(yearMonth.value)
            is Result.Error -> onLoadDateError(yearMonth.cause)
        }.exhaustive
    }

    private fun onLoadDateSuccess(yearMonth: Pair<Int?, Int?>) {
        Timber.d("Load date success. [Year: ${yearMonth.first}, Month: ${yearMonth.second}]")
        state = MoviesReducer.reduce(state, MoviesAction.DateSuccess(yearMonth.first, yearMonth.second))
    }

    private fun onLoadDateError(error: Exception) {
        Timber.w(error, "Load date error.")
        state = MoviesReducer.reduce(state, MoviesAction.DateError)
        sideEffect = MoviesReducer.once(MoviesAction.DateError)
    }

    /* MOVIES */

    private fun doLoadMovies() = state.movies == PagingData.empty<Movie>()

    private fun loadMovies(doLoadLoad: Boolean, intent: MoviesIntent.LoadMovies) {
        if (doLoadLoad) {
            loadMovies(intent.year, intent.month)
        } else {
            idleMovies(intent.year, intent.month)
        }
    }

    private fun loadMovies(year: Int? = null, month: Int? = null) = viewModelScope.launch {
        loadMoviesUseCase(LoadMoviesUseCase.Params(year, month))
            .cachedIn(viewModelScope)
            .collectLatest { onLoadMoviesSuccess(it) }
    }

    private fun onLoadMoviesSuccess(movies: PagingData<Movie>) {
        Timber.d("Load movies success. [Movies: $movies]")
        state = MoviesReducer.reduce(state, MoviesAction.MoviesSuccess(movies))
    }

    private fun onLoadMoviesError(error: Throwable, loadType: LoadType) {
        Timber.w(error, "Load movies error.")
        state = MoviesReducer.reduce(state, MoviesAction.MoviesError(loadType))
        sideEffect = MoviesReducer.once(MoviesAction.MoviesError(loadType))
    }

    private fun idleMovies(year: Int? = null, month: Int? = null) {
        state = MoviesReducer.reduce(state, MoviesAction.MoviesIdle(year, month))
    }

    private fun reloadMovies(year: Int? = null, month: Int? = null) {
        state = MoviesReducer.reduce(state, MoviesAction.MoviesReload(year, month))
        loadMovies(year, month)
    }

}
