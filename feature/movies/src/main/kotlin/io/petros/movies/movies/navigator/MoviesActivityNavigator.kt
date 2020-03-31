package io.petros.movies.movies.navigator

import io.petros.movies.core.view.SharedElementMovie
import io.petros.movies.movie_details.navigator.MovieDetailsLauncher

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
