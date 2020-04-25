package io.petros.movies.movies.di.koin

import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.movie_details.navigator.MovieDetailsLauncherImpl
import io.petros.movies.movies.MoviesViewModel
import io.petros.movies.movies.navigator.MoviesNavigator
import io.petros.movies.movies.navigator.MoviesNavigatorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private fun viewModelModule() = module {
    viewModel { MoviesViewModel(get()) }
}

private fun navigatorModule() = module {
    factory<MoviesNavigator> { (activity: AppCompatActivity) ->
        MoviesNavigatorImpl(MovieDetailsLauncherImpl(activity))
    }
}

fun moviesModule() = listOf(viewModelModule(), navigatorModule())
