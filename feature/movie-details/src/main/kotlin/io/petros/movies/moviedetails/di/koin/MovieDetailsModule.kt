@file:Suppress("Filename")

package io.petros.movies.moviedetails.di.koin

import io.petros.movies.moviedetails.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private fun viewModelModule() = module {
    viewModel { MovieDetailsViewModel(get()) }
}

fun movieDetailsModule() = listOf(
    viewModelModule(),
)
