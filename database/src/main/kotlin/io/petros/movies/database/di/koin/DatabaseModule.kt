package io.petros.movies.database.di.koin

import io.petros.movies.database.MoviesDatabase
import org.koin.dsl.module

@Suppress("unused")
fun databaseModule() = module {
    single { MoviesDatabase.getInstance(get()) }
}
