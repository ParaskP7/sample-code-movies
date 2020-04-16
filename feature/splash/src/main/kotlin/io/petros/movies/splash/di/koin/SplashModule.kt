package io.petros.movies.splash.di.koin

import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.movies.navigator.MoviesLauncherImpl
import io.petros.movies.splash.navigator.SplashNavigator
import io.petros.movies.splash.navigator.SplashNavigatorImpl
import org.koin.dsl.module

private val navigatorModule = module {
    factory<SplashNavigator> { (activity: AppCompatActivity) ->
        SplashNavigatorImpl(MoviesLauncherImpl(activity))
    }
}

val splashModule = listOf(navigatorModule)