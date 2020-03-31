package io.petros.movies.splash.di.koin

import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.movies.navigator.MoviesActivityLauncher
import io.petros.movies.splash.navigator.SplashActivityNavigator
import io.petros.movies.splash.navigator.SplashNavigator
import org.koin.dsl.module

private val navigatorModule = module {
    factory<SplashNavigator> { (activity: AppCompatActivity) ->
        SplashActivityNavigator(MoviesActivityLauncher(activity))
    }
}

val splashModule = listOf(navigatorModule)
