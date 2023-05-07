@file:Suppress("all")

package io.petros.movies.data.di.koin

import io.petros.movies.data.repository.movie.MoviesRepositoryImpl
import io.petros.movies.data.repository.settings.SettingsRepositoryImpl
import io.petros.movies.database.di.koin.databaseModule
//import io.petros.movies.datastore.di.koin.datastoreModule
import io.petros.movies.domain.repository.movie.MoviesRepository
import io.petros.movies.domain.repository.settings.SettingsRepository
import io.petros.movies.network.di.koin.networkModule
import org.koin.dsl.module

// TODO: Revert this!
private fun repositoryModule() = module {
//    factory<SettingsRepository> { SettingsRepositoryImpl(get()) }
    factory<SettingsRepository> { SettingsRepositoryImpl() }
    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

// TODO: Revert this!
fun dataModule(
    isDebug: Boolean,
    baseUrl: String,
    themoviedbApiKey: String,
) = listOf(
    repositoryModule(),
    networkModule(isDebug, baseUrl, themoviedbApiKey),
//    datastoreModule(),
    databaseModule(),
)
