package io.petros.movies.data.di.dagger

import dagger.Binds
import dagger.Module
import io.petros.movies.data.repository.movie.MoviesRepositoryImpl
import io.petros.movies.domain.repository.movie.MoviesRepository

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindMoviesRepository(moviesRepository: MoviesRepositoryImpl): MoviesRepository

}
