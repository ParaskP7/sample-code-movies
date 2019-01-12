package io.petros.movies.presentation.feature.splash.navigator

import io.petros.movies.presentation.feature.movies.navigator.MoviesLauncher

class SplashActivityNavigator constructor(
    private val moviesLauncher: MoviesLauncher
) : SplashNavigator {

    override fun navigate() {
        moviesLauncher.launch()
        moviesLauncher.finish()
    }

}
