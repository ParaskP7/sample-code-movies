package io.petros.movies.network.raw.movie

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.utils.MOVIE_DATE_FORMAT
import io.petros.movies.utils.toDate

data class MovieRaw(
    private val vote_count: Int,
    private val id: Int,
    private val vote_average: Double,
    private val title: String,
    private val backdrop_path: String?,
    private val overview: String,
    private val release_date: String?,
) {

    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            releaseDate = release_date?.toDate(MOVIE_DATE_FORMAT),
            voteAverage = vote_average,
            voteCount = vote_count,
            overview = overview,
            backdrop = backdrop_path?.let { IMAGE_URL_PREFIX + it },
        )
    }

    companion object {

        const val IMAGE_URL_PREFIX = "http://image.tmdb.org/t/p/w500"

    }

}
