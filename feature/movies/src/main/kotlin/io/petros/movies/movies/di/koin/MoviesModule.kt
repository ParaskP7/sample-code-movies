package io.petros.movies.movies.di.koin

import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.movie_details.navigator.MovieDetailsLauncherImpl
import io.petros.movies.movies.MoviesViewModel
import io.petros.movies.movies.navigator.MoviesNavigator
import io.petros.movies.movies.navigator.MoviesNavigatorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
}

private val navigatorModule = module {
    factory<MoviesNavigator> { (activity: AppCompatActivity) ->
        MoviesNavigatorImpl(MovieDetailsLauncherImpl(activity))
    }
}

val moviesModule = listOf(viewModelModule, navigatorModule)
