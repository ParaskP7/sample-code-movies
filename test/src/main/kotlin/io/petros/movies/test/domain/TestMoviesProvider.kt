package io.petros.movies.test.domain

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import java.util.*

class TestMoviesProvider {

    companion object {

        const val NEXT_PAGE = 2
        const val MOVIE_YEAR = 2018
        const val MOVIE_MONTH = 7

        private const val ID = 1
        private const val TITLE = "TITLE"
        private val RELEASE_DATE = GregorianCalendar(2018, Calendar.SEPTEMBER, 12).time
        private const val VOTE_AVERAGE = 10.0
        private const val VOTE_COUNT = 100
        private const val OVERVIEW = "OVERVIEW"
        private const val BACKDROP = "BACKDROP"

        @Suppress("LongParameterList")
        fun provideMovie(
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

        fun provideMoviesResultPage(
            nextPage: Int = NEXT_PAGE,
            movies: List<Movie> = arrayListOf(provideMovie(), provideMovie(), provideMovie())
        ): MoviesResultPage {
            return MoviesResultPage(
                nextPage,
                movies
            )
        }

    }

}
