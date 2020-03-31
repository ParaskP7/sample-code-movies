package io.petros.movies.movies.di.koin

import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.movie_details.navigator.MovieDetailsActivityLauncher
import io.petros.movies.movies.MoviesActivityViewModel
import io.petros.movies.movies.navigator.MoviesActivityNavigator
import io.petros.movies.movies.navigator.MoviesNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { MoviesActivityViewModel(get()) }
}

private val navigatorModule = module {
    factory<MoviesNavigator> { (activity: AppCompatActivity) ->
        MoviesActivityNavigator(MovieDetailsActivityLauncher(activity))
    }
}

val moviesModule = listOf(viewModelModule, navigatorModule)
