package io.petros.movies.domain.di.koin

import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { LoadMoviesUseCase(get()) }
}
