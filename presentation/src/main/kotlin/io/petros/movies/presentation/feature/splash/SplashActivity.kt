package io.petros.movies.presentation.feature.splash

import android.os.Bundle
import android.view.View
import io.petros.movies.presentation.feature.BaseActivity
import io.petros.movies.presentation.feature.splash.navigator.SplashNavigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : BaseActivity() { // MET

    private val splashNavigator: SplashNavigator by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashNavigator.navigate()
    }

    /* CONTRACT */

    override fun constructContentView(): View? = null

}
