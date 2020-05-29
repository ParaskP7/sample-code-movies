package io.petros.movies.movie_details

import dev.fanie.stateful.Stateful
import io.petros.movies.domain.model.movie.Movie
import java.util.*

@Stateful
data class MovieDetailsState(
    val status: MovieDetailsStatus,
    val movie: Movie
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
        val id: Int
    ) : MovieDetailsIntent()

}

sealed class MovieDetailsAction {

    object Idle : MovieDetailsAction()

    object Load : MovieDetailsAction()

    data class Success(
        val movie: Movie
    ) : MovieDetailsAction()

    object Error : MovieDetailsAction()

}

object MovieDetailsReducer {

    fun init() = MovieDetailsState(
        status = MovieDetailsStatus.Init,
        movie = moviePlaceholder
    )

    fun reduce(previousState: MovieDetailsState, action: MovieDetailsAction) = when (action) {
        is MovieDetailsAction.Idle -> previousState.copy(
            status = MovieDetailsStatus.Idle
        )
        is MovieDetailsAction.Load -> previousState.copy(
            status = MovieDetailsStatus.Loading
        )
        is MovieDetailsAction.Success -> previousState.copy(
            status = MovieDetailsStatus.Loaded,
            movie = action.movie
        )
        is MovieDetailsAction.Error -> previousState.copy(
            status = MovieDetailsStatus.Loaded,
            movie = moviePlaceholder
        )
    }

    @Suppress("UseIfInsteadOfWhen")
    fun once(action: MovieDetailsAction) = when (action) {
        is MovieDetailsAction.Error -> MovieDetailsSideEffect.Error
        else -> throw IllegalArgumentException(
            "This movie details action has no side effects associated with it. [Action: $action]"
        )
    }

}

val moviePlaceholder = Movie(
    0,
    "Title",
    GregorianCalendar(1, Calendar.JANUARY, 1).time,
    0.0,
    0,
    "Overview",
    null
)
