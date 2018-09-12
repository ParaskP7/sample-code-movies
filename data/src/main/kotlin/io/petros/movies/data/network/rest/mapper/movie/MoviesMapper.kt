package io.petros.movies.data.network.rest.mapper.movie

import android.content.Context
import io.petros.movies.data.R
import io.petros.movies.data.network.rest.response.movie.MovieResponse
import io.petros.movies.data.network.rest.response.movie.MoviesResultPageResponse
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.domain.toDate

class MoviesMapper {

    companion object {

        internal fun transform(context: Context, moviesResultPageResponse: MoviesResultPageResponse): MoviesResultPage {
            val movies = arrayListOf<Movie>()
            for (movieResponse in moviesResultPageResponse.results) {
                movies.add(movieResponse.toMovie(context))
            }
            return MoviesResultPage(movies)
        }

    }

}

private const val MOVIE_DATE_FORMAT = "yyyy-MM-dd"

private fun MovieResponse.toMovie(context: Context): Movie {
    return Movie(
        id = id,
        title = title,
        releaseDate = release_date.toDate(MOVIE_DATE_FORMAT),
        voteAverage = vote_average,
        voteCount = vote_count,
        backdrop = context.getString(R.string.rest_themoviedb_image_url) + backdrop_path
    )
}
