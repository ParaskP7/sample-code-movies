package io.petros.movies.data.network.rest.response.movie

import android.content.Context
import io.petros.movies.data.R
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.toDate
import io.petros.movies.domain.util.MOVIE_DATE_FORMAT

data class MovieRaw(
    private val vote_count: Int,
    private val id: Int,
    private val vote_average: Double,
    private val title: String,
    private val backdrop_path: String?,
    private val overview: String,
    private val release_date: String
) { // MIT

    fun toMovie(context: Context): Movie {
        return Movie(
            id = id,
            title = title,
            releaseDate = release_date.toDate(MOVIE_DATE_FORMAT),
            voteAverage = vote_average,
            voteCount = vote_count,
            overview = overview,
            backdrop = backdrop_path?.let { context.getString(R.string.rest_themoviedb_image_url) + it }
        )
    }

}
