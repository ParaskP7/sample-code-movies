package io.petros.movies.movies.navigator

import io.petros.movies.movie_details.navigator.MovieDetailsLauncher
import io.petros.movies.movie_details.navigator.SharedElementMovie

class MoviesActivityNavigator constructor(
    private val movieDetailsLauncher: MovieDetailsLauncher
) : MoviesNavigator {

    override fun navigate(movie: SharedElementMovie) {
        movieDetailsLauncher.launch(movie)
    }

    override fun finish() {
        movieDetailsLauncher.finish()
    }

}
