package io.petros.movies.presentation.feature.splash

import android.os.Bundle
import io.petros.movies.presentation.feature.BaseActivity
import io.petros.movies.presentation.feature.splash.navigator.SplashNavigator
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashActivityViewModel>() { // MET

    @Inject lateinit var splashNavigator: SplashNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashNavigator.navigate()
    }

    /* CONTRACT */

    override fun constructContentView(): Int? = null

    override fun constructViewModel(): Class<SplashActivityViewModel> = SplashActivityViewModel::class.java

}
