package io.petros.movies.app.di.koin

import io.petros.movies.movies.di.koin.moviesModule

private fun featureModules() = moviesModule()

fun appModule() = featureModules()
