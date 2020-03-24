package io.petros.movies.data.network.rest.response.movie

import io.petros.movies.domain.extensions.toDate
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.utils.MOVIE_DATE_FORMAT

data class MovieRaw(
    private val vote_count: Int,
    private val id: Int,
    private val vote_average: Double,
    private val title: String,
    private val backdrop_path: String?,
    private val overview: String,
    private val release_date: String
) { // MIT

    companion object {

        private const val IMAGE_URL_PREFIX = "http://image.tmdb.org/t/p/w500"

    }

    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            releaseDate = release_date.toDate(MOVIE_DATE_FORMAT),
            voteAverage = vote_average,
            voteCount = vote_count,
            overview = overview,
            backdrop = backdrop_path?.let { IMAGE_URL_PREFIX + it }
        )
    }

}
