package io.petros.movies.presentation.di.koin

import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.movie_details.navigator.MovieDetailsActivityLauncher
import io.petros.movies.movies.MoviesActivityViewModel
import io.petros.movies.movies.navigator.MoviesActivityLauncher
import io.petros.movies.movies.navigator.MoviesActivityNavigator
import io.petros.movies.movies.navigator.MoviesNavigator
import io.petros.movies.splash.navigator.SplashActivityNavigator
import io.petros.movies.splash.navigator.SplashNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    viewModel { MoviesActivityViewModel(get()) }
}

private val navigatorModule = module {
    factory<SplashNavigator> { (activity: AppCompatActivity) ->
        SplashActivityNavigator(MoviesActivityLauncher(activity))
    }
    factory<MoviesNavigator> { (activity: AppCompatActivity) ->
        MoviesActivityNavigator(MovieDetailsActivityLauncher(activity))
    }
}

val presentationModule = listOf(appModule, navigatorModule)
