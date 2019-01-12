package io.petros.movies.presentation.feature.movies.navigator

import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsLauncher
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie

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
