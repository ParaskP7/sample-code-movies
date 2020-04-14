package io.petros.movies.test.domain

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import java.util.*

private const val ID = 1
private const val TITLE = "TITLE"
private val RELEASE_DATE = GregorianCalendar(2018, Calendar.SEPTEMBER, 12).time
private const val VOTE_AVERAGE = 10.0
private const val VOTE_COUNT = 100
private const val OVERVIEW = "OVERVIEW"
private const val BACKDROP = "BACKDROP"

fun moviesPage(
    nextPage: Int = 2,
    movies: List<Movie> = arrayListOf(movie(), movie(), movie())
): MoviesPage {
    return MoviesPage(
        nextPage,
        movies
    )
}

@Suppress("LongParameterList")
fun movie(
    id: Int = ID,
    title: String = TITLE,
    releaseDate: Date = RELEASE_DATE,
    voteAverage: Double = VOTE_AVERAGE,
    voteCount: Int = VOTE_COUNT,
    overview: String = OVERVIEW,
    backdrop: String = BACKDROP
): Movie {
    return Movie(
        id,
        title,
        releaseDate,
        voteAverage,
        voteCount,
        overview,
        backdrop
    )
}
