package io.petros.movies.app.di.koin

import io.petros.movies.moviedetails.di.koin.movieDetailsModule
import io.petros.movies.moviedetailscompose.di.koin.movieDetailsComposeModule
import io.petros.movies.movies.di.koin.moviesModule

private fun featureModules() = moviesModule() + movieDetailsModule() + movieDetailsComposeModule()

fun appModule() = featureModules()
