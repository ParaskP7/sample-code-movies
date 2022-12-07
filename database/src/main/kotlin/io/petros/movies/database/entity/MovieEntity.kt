package io.petros.movies.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.utils.MOVIE_DATE_FORMAT
import io.petros.movies.utils.toDate

@Suppress("DataClassContainsFunctions")
@Entity(tableName = "movies")
data class MovieEntity(
    // The below fields are for pagination purposes only.
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?,
    // The below fields are for offline purposes only.
    val year: Int?,
    val month: Int?,
    // The below fields are 'Movie' data class related fields.
    val movieId: Int,
    val title: String,
    val releaseDate: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String,
    val backdrop: String?,
) {

    fun toMovie() = Movie(
        id = movieId,
        title = title,
        releaseDate = releaseDate?.toDate(MOVIE_DATE_FORMAT),
        voteAverage = voteAverage,
        voteCount = voteCount,
        overview = overview,
        backdrop = backdrop,
    )

    companion object {

        fun from(
            prevPage: Int?,
            nextPage: Int?,
            year: Int?,
            month: Int?,
            movie: Movie,
        ) = MovieEntity(
            prevPage = prevPage,
            nextPage = nextPage,
            year = year,
            month = month,
            movieId = movie.id,
            title = movie.title,
            releaseDate = movie.releaseDate?.toDate(MOVIE_DATE_FORMAT),
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            overview = movie.overview,
            backdrop = movie.backdrop,
        )

    }

}

@Suppress("UnnecessaryParentheses")
fun MovieEntity?.toDate(): Pair<Int?, Int?> = this?.let { year to month } ?: (null to null)
