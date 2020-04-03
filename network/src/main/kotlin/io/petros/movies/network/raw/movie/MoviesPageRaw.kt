package io.petros.movies.network.raw.movie

import io.petros.movies.domain.model.movie.MoviesPage

data class MoviesPageRaw(
    private val page: Int,
    private val total_pages: Int,
    private val results: List<MovieRaw>
) { // MIT

    fun toMoviesPage(): MoviesPage {
        return MoviesPage(
            if (page < total_pages) page + 1 else null,
            results.map { it.toMovie() }
        )
    }

}
