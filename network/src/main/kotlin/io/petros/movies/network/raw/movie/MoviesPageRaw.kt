package io.petros.movies.network.raw.movie

import io.petros.movies.domain.model.movie.MoviesPage

data class MoviesPageRaw(
    private val results: List<MovieRaw>,
) {

    fun toMoviesPage(): MoviesPage {
        return MoviesPage(
            results.map { it.toMovie() },
        )
    }

}
