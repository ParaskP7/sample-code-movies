@file:Suppress("Filename")

package io.petros.movies.domain.di.koin

import io.petros.movies.domain.interactor.movie.LoadDateUseCase
import io.petros.movies.domain.interactor.movie.LoadMovieUseCase
import io.petros.movies.domain.interactor.movie.LoadMoviesUseCase
import org.koin.dsl.module

private fun useCaseModule() = module {
    factory { LoadDateUseCase(get()) }
    factory { LoadMoviesUseCase(get()) }
    factory { LoadMovieUseCase(get()) }
}

fun domainModule() = listOf(
    useCaseModule(),
)
