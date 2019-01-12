package io.petros.movies.presentation.di.koin

import io.petros.movies.presentation.feature.movies.MoviesActivityViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { MoviesActivityViewModel(get()) }
}
