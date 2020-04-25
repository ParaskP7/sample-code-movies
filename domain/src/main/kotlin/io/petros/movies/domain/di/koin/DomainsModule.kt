package io.petros.movies.domain.di.koin

import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import org.koin.dsl.module

private fun useCaseModule() = module {
    factory { LoadMoviesUseCase(get()) }
}

fun domainModule() = listOf(
    useCaseModule()
)
