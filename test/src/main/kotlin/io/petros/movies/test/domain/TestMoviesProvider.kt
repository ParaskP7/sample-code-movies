package io.petros.movies.test.domain

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesPage
import java.util.*

fun moviesPage(
    nextPage: Int? = 2,
    movies: List<Movie> = arrayListOf(movie(), movie(), movie())
): MoviesPage {
    return MoviesPage(
        nextPage,
        movies
    )
}

@Suppress("LongParameterList")
fun movie(
    id: Int = 419_704,
    title: String = "Ad Astra",
    releaseDate: Date = GregorianCalendar(2019, Calendar.SEPTEMBER, 17).time,
    voteAverage: Double = 6.0,
    voteCount: Int = 2958,
    overview: String = "The near future, a time when both hope and hardships drive humanity to look to the stars " +
            "and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut " +
            "Roy McBride undertakes a mission across the immensity of space and its many perils to " +
            "uncover the truth about a lost expedition that decades before boldly faced emptiness and " +
            "silence in search of the unknown.",
    backdrop: String? = "http://image.tmdb.org/t/p/w500/5BwqwxMEjeFtdknRV792Svo0K1v.jpg"
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
