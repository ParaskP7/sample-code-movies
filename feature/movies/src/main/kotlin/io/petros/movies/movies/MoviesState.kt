package io.petros.movies.movies

import androidx.paging.LoadType
import androidx.paging.PagingData
import dev.fanie.stateful.Stateful
import io.petros.movies.domain.model.movie.Movie

@Stateful
data class MoviesState(
    val year: Int?,
    val month: Int?,
    val status: MoviesStatus,
    val movies: PagingData<Movie>,
)

sealed class MoviesStatus {

    object Init : MoviesStatus()

    object Idle : MoviesStatus()

    object Loading : MoviesStatus()

    object Loaded : MoviesStatus()

}

sealed class MoviesSideEffect {

    object MoviesRefreshError : MoviesSideEffect()

    object MoviesAppendError : MoviesSideEffect()

    object MoviesPrependError : MoviesSideEffect()

}

sealed class MoviesIntent {

    data class IdleMovies(
        val year: Int? = null,
        val month: Int? = null,
    ) : MoviesIntent()

    data class LoadMovies(
        val year: Int? = null,
        val month: Int? = null,
    ) : MoviesIntent()

    data class ErrorMovies(
        val error: Throwable,
        val loadType: LoadType,
    ) : MoviesIntent()

    data class ReloadMovies(
        val year: Int? = null,
        val month: Int? = null,
    ) : MoviesIntent()

}

sealed class MoviesAction {

    data class Idle(
        val year: Int?,
        val month: Int?,
    ) : MoviesAction()

    data class Load(
        val year: Int?,
        val month: Int?,
    ) : MoviesAction()

    data class Reload(
        val year: Int?,
        val month: Int?,
    ) : MoviesAction()

    data class Success(
        val movies: PagingData<Movie>,
    ) : MoviesAction()

    data class Error(
        val loadType: LoadType,
    ) : MoviesAction()

}

object MoviesReducer {

    fun init() = MoviesState(
        year = null,
        month = null,
        status = MoviesStatus.Init,
        movies = PagingData.empty(),
    )

    fun reduce(previousState: MoviesState, action: MoviesAction) = when (action) {
        is MoviesAction.Idle -> previousState.copy(
            year = action.year,
            month = action.month,
            status = MoviesStatus.Idle,
        )
        is MoviesAction.Load -> previousState.copy(
            year = action.year,
            month = action.month,
            status = MoviesStatus.Loading,
        )
        is MoviesAction.Reload -> previousState.copy(
            year = action.year,
            month = action.month,
            movies = PagingData.empty(),
        )
        is MoviesAction.Success -> previousState.copy(
            status = MoviesStatus.Loaded,
            movies = action.movies,
        )
        is MoviesAction.Error -> previousState.copy(
            status = MoviesStatus.Loaded,
        )
    }

    @Suppress("UseIfInsteadOfWhen")
    fun once(action: MoviesAction) = when (action) {
        is MoviesAction.Error -> when (action.loadType) {
            LoadType.REFRESH -> MoviesSideEffect.MoviesRefreshError
            LoadType.APPEND -> MoviesSideEffect.MoviesAppendError
            LoadType.PREPEND -> MoviesSideEffect.MoviesPrependError
        }
        else -> throw IllegalArgumentException(
            "This movies action has no side effects associated with it. [Action: $action]"
        )
    }

}
