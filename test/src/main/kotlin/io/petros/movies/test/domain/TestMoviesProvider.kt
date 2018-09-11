package io.petros.movies.test.domain

import io.petros.movies.domain.model.movie.Movie

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

    }

}
