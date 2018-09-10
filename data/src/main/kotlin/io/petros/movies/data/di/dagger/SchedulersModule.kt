package io.petros.movies.data.di.dagger

import dagger.Module
import dagger.Provides
import io.petros.movies.data.reactive.rx.RxSchedulersProvider
import io.petros.movies.domain.reactive.rx.RxSchedulers

@Module
class SchedulersModule {

    @Provides
    fun provideRxSchedulers(): RxSchedulers = RxSchedulersProvider.rxSchedulers()

}
