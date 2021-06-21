package io.petros.movies.datastore.di.koin

import io.petros.movies.datastore.MoviesDatastore
import org.koin.dsl.module

fun datastoreModule() = module {
    single { MoviesDatastore(get()) }
}
