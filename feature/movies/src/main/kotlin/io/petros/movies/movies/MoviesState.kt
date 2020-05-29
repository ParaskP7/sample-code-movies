package io.petros.movies.movies

import dev.fanie.stateful.Stateful
import io.petros.movies.domain.model.common.PaginationData
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage

@Stateful
data class MoviesState(
    val year: Int?,
    val month: Int?,
    val status: MoviesStatus,
    val movies: PaginationData<Movie>
)

sealed class MoviesStatus {

    object Init : MoviesStatus()

    object Idle : MoviesStatus()

    object Loading : MoviesStatus()

    object Loaded : MoviesStatus()

}

sealed class MoviesSideEffect {

    object Error : MoviesSideEffect()

}

sealed class MoviesIntent {

    data class IdleMovies(
        val year: Int? = null,
        val month: Int? = null
    ) : MoviesIntent()

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

    data class Idle(
        val year: Int?,
        val month: Int?
    ) : MoviesAction()

    data class Load(
        val year: Int?,
        val month: Int?
    ) : MoviesAction()

    data class Reload(
        val year: Int?,
        val month: Int?
    ) : MoviesAction()

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
            year = action.year,
            month = action.month,
            status = MoviesStatus.Idle
        )
        is MoviesAction.Load -> previousState.copy(
            year = action.year,
            month = action.month,
            status = MoviesStatus.Loading
        )
        is MoviesAction.Reload -> previousState.copy(
            year = action.year,
            month = action.month,
            movies = PaginationData()
        )
        is MoviesAction.Success -> previousState.copy(
            status = MoviesStatus.Loaded,
            movies = PaginationData(
                previousState.movies.allPageItems + action.movies.items,
                action.movies,
                action.movies.nextPage
            )
        )
        is MoviesAction.Error -> previousState.copy(
            status = MoviesStatus.Loaded,
            movies = PaginationData(
                previousState.movies.allPageItems,
                MoviesPage(previousState.movies.nextPage, emptyList()),
                previousState.movies.nextPage
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
