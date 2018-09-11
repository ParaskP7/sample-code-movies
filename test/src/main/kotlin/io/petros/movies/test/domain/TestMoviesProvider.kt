package io.petros.movies.test.domain

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesResultPage

class TestMoviesProvider {

    companion object {

        private const val TITLE = "TITLE"

        fun provideMovie(
            title: String = TITLE
        ): Movie {
            return Movie(
                title
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
