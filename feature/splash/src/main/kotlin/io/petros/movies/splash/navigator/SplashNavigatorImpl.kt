package io.petros.movies.splash.navigator

import io.petros.movies.movies.navigator.MoviesLauncher

class SplashNavigatorImpl(
    private val moviesLauncher: MoviesLauncher
) : SplashNavigator {

    override fun navigate() {
        moviesLauncher.launch()
        moviesLauncher.finish()
    }

}
