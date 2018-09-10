package io.petros.movies.presentation.feature.movies

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.petros.movies.presentation.di.dagger.activity.SubModuleBinding
import io.petros.movies.presentation.di.dagger.viewmodel.ViewModelKey

@Module
abstract class MoviesActivitySubModule : SubModuleBinding<MoviesActivity> {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesActivityViewModel::class)
    abstract fun bindMoviesActivityViewModel(moviesActivityViewModel: MoviesActivityViewModel): ViewModel

}
