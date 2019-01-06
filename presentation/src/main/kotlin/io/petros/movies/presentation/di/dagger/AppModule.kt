package io.petros.movies.presentation.di.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.petros.movies.data.di.dagger.NetworkModule
import io.petros.movies.data.di.dagger.RepositoriesModule
import io.petros.movies.presentation.App

@Module(
    includes = [
        RepositoriesModule::class,
        NetworkModule::class
    ]
)
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}
