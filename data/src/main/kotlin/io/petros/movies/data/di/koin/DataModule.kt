package io.petros.movies.data.di.koin

import io.petros.movies.data.repository.movie.MoviesRepositoryImpl
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.network.di.koin.networkModule
import org.koin.dsl.module

private val repositoryModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
}

val dataModule = listOf(repositoryModule, networkModule)
