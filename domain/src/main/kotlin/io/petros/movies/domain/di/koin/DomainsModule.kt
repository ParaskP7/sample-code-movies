package io.petros.movies.domain.di.koin

import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import org.koin.dsl.module

private val useCaseModule = module {
    factory { LoadMoviesUseCase(get()) }
}

val domainModule = listOf(useCaseModule)
