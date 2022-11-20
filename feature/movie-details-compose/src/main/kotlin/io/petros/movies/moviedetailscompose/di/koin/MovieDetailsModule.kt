@file:Suppress("Filename")

package io.petros.movies.moviedetailscompose.di.koin

import io.petros.movies.moviedetailscompose.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private fun viewModelModule() = module {
    viewModel { MovieDetailsViewModel(get()) }
}

fun movieDetailsComposeModule() = listOf(
    viewModelModule(),
)
