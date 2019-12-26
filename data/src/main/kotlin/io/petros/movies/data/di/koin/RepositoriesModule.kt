package io.petros.movies.data.di.koin

import io.petros.movies.data.repository.movie.MoviesRepositoryImpl
import io.petros.movies.domain.repository.movie.MoviesRepository
import org.koin.dsl.module

val repositoriesModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
}
