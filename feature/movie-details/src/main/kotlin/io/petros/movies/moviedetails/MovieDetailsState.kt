package io.petros.movies.moviedetails

import io.petros.movies.domain.model.movie.Movie

data class MovieDetailsState(
    val status: MovieDetailsStatus,
    val movie: Movie,
)

sealed class MovieDetailsStatus {

    object Init : MovieDetailsStatus()

    object Idle : MovieDetailsStatus()

    object Loading : MovieDetailsStatus()

    object Loaded : MovieDetailsStatus()

}

sealed class MovieDetailsSideEffect {

    object Error : MovieDetailsSideEffect()

}

sealed class MovieDetailsIntent {

    object IdleMovies : MovieDetailsIntent()

    data class LoadMovie(
        val id: Int,
    ) : MovieDetailsIntent()

}

sealed class MovieDetailsAction {

    object Idle : MovieDetailsAction()

    object Load : MovieDetailsAction()

    data class Success(
        val movie: Movie,
    ) : MovieDetailsAction()

    object Error : MovieDetailsAction()

}

object MovieDetailsReducer {

    fun init() = MovieDetailsState(
        status = MovieDetailsStatus.Init,
        movie = Movie.Default,
    )

    fun reduce(previousState: MovieDetailsState, action: MovieDetailsAction) = when (action) {
        is MovieDetailsAction.Idle -> previousState.copy(
            status = MovieDetailsStatus.Idle,
        )
        is MovieDetailsAction.Load -> previousState.copy(
            status = MovieDetailsStatus.Loading,
        )
        is MovieDetailsAction.Success -> previousState.copy(
            status = MovieDetailsStatus.Loaded,
            movie = action.movie,
        )
        is MovieDetailsAction.Error -> previousState.copy(
            status = MovieDetailsStatus.Loaded,
            movie = Movie.Default,
        )
    }

    fun once(action: MovieDetailsAction) = when (action) {
        is MovieDetailsAction.Idle -> throw IllegalArgumentException(
            "This movie details action has no side effects associated with this idle action."
        )
        is MovieDetailsAction.Load -> throw IllegalArgumentException(
            "This movie details action has no side effects associated with this load action."
        )
        is MovieDetailsAction.Success -> throw IllegalArgumentException(
            "This movie details action has no side effects associated with this success action."
        )
        is MovieDetailsAction.Error -> MovieDetailsSideEffect.Error
    }

}
