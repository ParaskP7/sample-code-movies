package io.petros.movies.presentation.feature.movie

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.petros.movies.presentation.di.dagger.activity.SubModuleBinding
import io.petros.movies.presentation.di.dagger.viewmodel.ViewModelKey

@Module
@Suppress("FunctionMaxLength")
abstract class MovieDetailsActivitySubModule : SubModuleBinding<MovieDetailsActivity> {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsActivityViewModel::class)
    abstract fun bindMovieDetailsActivityViewModel(movieDetailsActivityViewModel: MovieDetailsActivityViewModel): ViewModel

}
