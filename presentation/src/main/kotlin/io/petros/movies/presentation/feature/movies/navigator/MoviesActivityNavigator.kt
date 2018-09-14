package io.petros.movies.presentation.feature.movies.navigator

import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsLauncher
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie
import io.petros.movies.presentation.feature.navigator.ActivityNavigator
import javax.inject.Inject

class MoviesActivityNavigator @Inject constructor(
    private val movieDetailsLauncher: MovieDetailsLauncher
) : ActivityNavigator(), MoviesNavigator {

    override fun navigate(movie: SharedElementMovie) {
        movieDetailsLauncher.launch(movie)
    }

}
