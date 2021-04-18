package io.petros.movies.movies

import androidx.paging.LoadType
import androidx.paging.PagingData
import io.petros.movies.domain.model.movie.Movie

data class MoviesState(
    val year: Int?,
    val month: Int?,
    val movies: PagingData<Movie>,
)

sealed class MoviesSideEffect {

    object DateError : MoviesSideEffect()

    object MoviesRefreshError : MoviesSideEffect()

    object MoviesAppendError : MoviesSideEffect()

    object MoviesPrependError : MoviesSideEffect()

}

sealed class MoviesIntent {

    object LoadDate : MoviesIntent()

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

    data class DateSuccess(
        val year: Int?,
        val month: Int?,
    ) : MoviesAction()

    object DateError : MoviesAction()

    data class MoviesIdle(
        val year: Int?,
        val month: Int?,
    ) : MoviesAction()

    data class MoviesReload(
        val year: Int?,
        val month: Int?,
    ) : MoviesAction()

    data class MoviesSuccess(
        val movies: PagingData<Movie>,
    ) : MoviesAction()

    data class MoviesError(
        val loadType: LoadType,
    ) : MoviesAction()

}

object MoviesReducer {

    fun init() = MoviesState(
        year = null,
        month = null,
        movies = PagingData.empty(),
    )

    fun reduce(previousState: MoviesState, action: MoviesAction) = when (action) {
        is MoviesAction.DateSuccess -> previousState.copy(
            year = action.year,
            month = action.month,
        )
        is MoviesAction.DateError -> previousState.copy(
            year = previousState.year,
            month = previousState.month,
        )
        is MoviesAction.MoviesIdle -> previousState.copy(
            year = action.year,
            month = action.month,
        )
        is MoviesAction.MoviesReload -> previousState.copy(
            year = action.year,
            month = action.month,
            movies = PagingData.empty(),
        )
        is MoviesAction.MoviesSuccess -> previousState.copy(
            movies = action.movies,
        )
        is MoviesAction.MoviesError -> previousState.copy()
    }

    @Suppress("UseIfInsteadOfWhen")
    fun once(action: MoviesAction) = when (action) {
        is MoviesAction.DateError -> MoviesSideEffect.DateError
        is MoviesAction.MoviesError -> when (action.loadType) {
            LoadType.REFRESH -> MoviesSideEffect.MoviesRefreshError
            LoadType.APPEND -> MoviesSideEffect.MoviesAppendError
            LoadType.PREPEND -> MoviesSideEffect.MoviesPrependError
        }
        else -> throw IllegalArgumentException(
            "This movies action has no side effects associated with it. [Action: $action]"
        )
    }

}
