package io.petros.movies.presentation.di.dagger.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.petros.movies.presentation.feature.movies.MoviesActivity
import io.petros.movies.presentation.feature.movies.MoviesActivitySubModule

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [MoviesActivitySubModule::class])
    abstract fun bindsMoviesActivity(): MoviesActivity

}
