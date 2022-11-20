@file:Suppress("Filename")

package io.petros.movies.movie_details_compose.di.koin

import io.petros.movies.movie_details_compose.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private fun viewModelModule() = module {
    viewModel { MovieDetailsViewModel(get()) }
}

fun movieDetailsComposeModule() = listOf(
    viewModelModule(),
)
