package io.petros.movies.app.di.koin

import io.petros.movies.movie_details.di.koin.movieDetailsModule
import io.petros.movies.movies.di.koin.moviesModule

private fun featureModules() = moviesModule() + movieDetailsModule()

fun appModule() = featureModules()
