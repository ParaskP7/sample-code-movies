package io.petros.movies.presentation.di.dagger.activity

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.petros.movies.presentation.di.dagger.viewmodel.ViewModelFactory
import io.petros.movies.presentation.feature.movie.MovieDetailsActivity
import io.petros.movies.presentation.feature.movie.MovieDetailsActivitySubModule
import io.petros.movies.presentation.feature.movies.MoviesActivity
import io.petros.movies.presentation.feature.movies.MoviesActivitySubModule
import io.petros.movies.presentation.feature.splash.SplashActivity
import io.petros.movies.presentation.feature.splash.SplashActivitySubModule

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [SplashActivitySubModule::class])
    abstract fun bindsSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [MoviesActivitySubModule::class])
    abstract fun bindsMoviesActivity(): MoviesActivity

    @ContributesAndroidInjector(modules = [MovieDetailsActivitySubModule::class])
    abstract fun bindsMovieDetailsActivity(): MovieDetailsActivity

    /* VIEW MODEL */

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
