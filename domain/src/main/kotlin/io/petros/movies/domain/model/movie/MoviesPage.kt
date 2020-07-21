package io.petros.movies.domain.model.movie

data class MoviesPage(
    val nextPage: Int?,
    val items: List<Movie>,
)
