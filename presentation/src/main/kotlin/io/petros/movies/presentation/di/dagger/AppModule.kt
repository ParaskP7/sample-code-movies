package io.petros.movies.presentation.di.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.petros.movies.presentation.App

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}
