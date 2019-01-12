package io.petros.movies.presentation.di.koin

import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsActivityLauncher
import io.petros.movies.presentation.feature.movies.navigator.MoviesActivityLauncher
import io.petros.movies.presentation.feature.movies.navigator.MoviesActivityNavigator
import io.petros.movies.presentation.feature.movies.navigator.MoviesNavigator
import io.petros.movies.presentation.feature.splash.navigator.SplashActivityNavigator
import io.petros.movies.presentation.feature.splash.navigator.SplashNavigator
import org.koin.dsl.module.module

val navigatorModule = module {
    factory<SplashNavigator> { (activity: AppCompatActivity) ->
        SplashActivityNavigator(MoviesActivityLauncher(activity))
    }
    factory<MoviesNavigator> { (activity: AppCompatActivity) ->
        MoviesActivityNavigator(MovieDetailsActivityLauncher(activity))
    }
}
