package io.petros.movies.movies

import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import io.petros.movies.domain.model.movie.MoviesStatus

data class MoviesState(
    val year: Int?,
    val month: Int?,
    val status: MoviesStatus,
    val movies: PaginationData<Movie>
)

sealed class MoviesSideEffect {

    object Error : MoviesSideEffect()

}

sealed class MoviesIntent {

    object IdleMovies : MoviesIntent()

    data class LoadMovies(
        val year: Int? = null,
        val month: Int? = null,
        val page: Int? = null
    ) : MoviesIntent()

    data class ReloadMovies(
        val year: Int? = null,
        val month: Int? = null
    ) : MoviesIntent()

}

sealed class MoviesAction {

    object Idle : MoviesAction()

    data class Load(
        val year: Int?,
        val month: Int?
    ) : MoviesAction()

    object Reload : MoviesAction()

    data class Success(
        val movies: MoviesPage
    ) : MoviesAction()

    object Error : MoviesAction()

}

object MoviesReducer {

    fun init() = MoviesState(
        year = null,
        month = null,
        status = MoviesStatus.Init,
        movies = PaginationData()
    )

    fun reduce(previousState: MoviesState, action: MoviesAction) = when (action) {
        is MoviesAction.Idle -> previousState.copy(
            status = MoviesStatus.Idle
        )
        is MoviesAction.Load -> previousState.copy(
            year = action.year,
            month = action.month,
            status = MoviesStatus.Loading
        )
        is MoviesAction.Reload -> previousState.copy(
            year = null,
            month = null,
            movies = PaginationData()
        )
        is MoviesAction.Success -> previousState.copy(
            status = MoviesStatus.Loaded,
            movies = previousState.movies.addPage(
                action.movies
            )
        )
        is MoviesAction.Error -> previousState.copy(
            status = MoviesStatus.Loaded,
            movies = previousState.movies.addPage(
                MoviesPage(previousState.movies.nextPage(), emptyList())
            )
        )
    }

    @Suppress("UseIfInsteadOfWhen")
    fun once(action: MoviesAction) = when (action) {
        is MoviesAction.Error -> MoviesSideEffect.Error
        else -> throw IllegalArgumentException(
            "This movies action has no side effects associated with it. [Action: $action]"
        )
    }

}
