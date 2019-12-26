package io.petros.movies.presentation.di.koin

import io.petros.movies.presentation.feature.movies.MoviesActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MoviesActivityViewModel(get()) }
}
