package io.petros.movies.presentation.di.dagger.activity

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import io.petros.movies.presentation.feature.navigator.ActivityLauncher
import io.petros.movies.presentation.feature.navigator.ActivityNavigator
import io.petros.movies.presentation.feature.navigator.Launcher
import io.petros.movies.presentation.feature.navigator.Navigator

@Module
interface SubModuleBinding<in Activity : AppCompatActivity> {

    @Binds
    fun bindActivity(activity: Activity): AppCompatActivity

    @Binds
    fun bindNavigator(navigator: ActivityNavigator): Navigator

    @Binds
    fun bindLauncher(launcher: ActivityLauncher): Launcher

}
