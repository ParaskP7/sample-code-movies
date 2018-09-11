package io.petros.movies.data.network.rest.mapper.movie

import io.petros.movies.data.network.rest.response.movie.MovieResponse
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageResponse
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage

class MoviesMapper {

    companion object {

        internal fun transform(moviesResultPageResponse: MoviesResultPageResponse): MoviesResultPage {
            val movies = arrayListOf<Movie>()
            for (movieResponse in moviesResultPageResponse.results) {
                movies.add(movieResponse.toMovie())
            }
            return MoviesResultPage(movies)
        }

    }

}

fun MovieResponse.toMovie(): Movie {
    return Movie(
        title = title
    )
}
