package io.petros.movies.movie_details.di.koin

import io.petros.movies.movie_details.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private fun viewModelModule() = module {
    viewModel { MovieDetailsViewModel(get()) }
}

fun movieDetailsModule() = listOf(
    viewModelModule(),
)
