package io.petros.movies.presentation.feature.splash

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.petros.movies.presentation.di.dagger.activity.SubModuleBinding
import io.petros.movies.presentation.di.dagger.viewmodel.ViewModelKey
import io.petros.movies.presentation.feature.movies.navigator.MoviesActivityLauncher
import io.petros.movies.presentation.feature.movies.navigator.MoviesLauncher
import io.petros.movies.presentation.feature.splash.navigator.SplashActivityNavigator
import io.petros.movies.presentation.feature.splash.navigator.SplashNavigator

@Module
abstract class SplashActivitySubModule : SubModuleBinding<SplashActivity> {

    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun bindSplashActivityViewModel(splashActivityViewModel: SplashActivityViewModel): ViewModel

    @Binds
    abstract fun bindSplashNavigator(splashNavigator: SplashActivityNavigator): SplashNavigator

    @Binds
    abstract fun bindMoviesLauncher(moviesLauncher: MoviesActivityLauncher): MoviesLauncher

}
