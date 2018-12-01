package io.petros.movies.data.network.rest.mapper.movie

import android.content.Context
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageResponse
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage

class MoviesMapper private constructor() { // MIT

    companion object {

        internal fun transform(
            context: Context,
            moviesResultPageResponse: MoviesResultPageResponse
        ): MoviesResultPage {
            val movies = arrayListOf<Movie>()
            for (movieResponse in moviesResultPageResponse.results) {
                movies.add(movieResponse.toMovie(context))
            }
            return MoviesResultPage(
                moviesResultPageResponse.nextPage(),
                movies
            )
        }

    }

}
