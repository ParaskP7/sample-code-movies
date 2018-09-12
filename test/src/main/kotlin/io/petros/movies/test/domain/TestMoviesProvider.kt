package io.petros.movies.test.domain

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage
import java.util.*

class TestMoviesProvider {

    companion object {

        private const val ID = 1
        private const val TITLE = "TITLE"
        private val RELEASE_DATE = GregorianCalendar(2018, Calendar.SEPTEMBER, 12).time
        private const val VOTE_AVERAGE = 10.0
        private const val VOTE_COUNT = 100
        private const val BACKDROP = "BACKDROP"

        fun provideMovie(
            id: Int = ID,
            title: String = TITLE,
            releaseDate: Date = RELEASE_DATE,
            voteAverage: Double = VOTE_AVERAGE,
            voteCount: Int = VOTE_COUNT,
            backdrop: String = BACKDROP
        ): Movie {
            return Movie(
                id,
                title,
                releaseDate,
                voteAverage,
                voteCount,
                backdrop
            )
        }

        fun provideMoviesResultPage(
            movies: List<Movie> = arrayListOf(provideMovie(), provideMovie(), provideMovie())
        ): MoviesResultPage {
            return MoviesResultPage(
                movies
            )
        }

    }

}
