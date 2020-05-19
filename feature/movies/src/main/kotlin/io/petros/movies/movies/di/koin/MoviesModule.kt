package io.petros.movies.movies.di.koin

import io.petros.movies.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private fun viewModelModule() = module {
    viewModel { MoviesViewModel(get()) }
}

fun moviesModule() = listOf(
    viewModelModule()
)
