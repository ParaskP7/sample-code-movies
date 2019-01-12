package io.petros.movies.data.network.rest.response.movie

import android.content.Context
import io.petros.movies.domain.model.movie.MoviesResultPage

@Suppress("ConstructorParameterNaming")
data class MoviesResultPageRaw(
    private val page: Int,
    private val total_pages: Int,
    private val results: List<MovieRaw>
) { // MIT

    fun toMoviesResultPage(context: Context): MoviesResultPage {
        return MoviesResultPage(
            if (page < total_pages) page + 1 else null,
            results.map { it.toMovie(context) }
        )
    }

}
