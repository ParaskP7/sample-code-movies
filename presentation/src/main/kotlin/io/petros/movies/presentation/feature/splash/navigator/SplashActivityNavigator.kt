package io.petros.movies.presentation.feature.splash.navigator

import io.petros.movies.presentation.feature.movies.navigator.MoviesLauncher
import io.petros.movies.presentation.feature.navigator.ActivityNavigator
import javax.inject.Inject

class SplashActivityNavigator @Inject constructor(
    private val moviesLauncher: MoviesLauncher
) : ActivityNavigator(), SplashNavigator {

    override fun navigate() {
        moviesLauncher.launch()
        launcher.finish()
    }

}
