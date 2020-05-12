package io.petros.movies.domain.model.movie

sealed class MoviesStatus {

    object Init : MoviesStatus()

    object Loading : MoviesStatus()

    object Loaded : MoviesStatus()

}
