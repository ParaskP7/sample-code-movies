package io.petros.movies.presentation.di.dagger

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.petros.movies.presentation.App
import io.petros.movies.presentation.di.dagger.activity.ActivitiesModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    override fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent

    }

}
