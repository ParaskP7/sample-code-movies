package io.petros.movies.movies.navigator

import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movie_details.navigator.MovieDetailsLauncher

class MoviesNavigatorImpl(
    private val movieDetailsLauncher: MovieDetailsLauncher
) : MoviesNavigator {

    override fun navigate(movie: Movie) {
        movieDetailsLauncher.launch(movie)
    }

    override fun finish() {
        movieDetailsLauncher.finish()
    }

}
