package io.petros.movies.presentation.di.dagger.activity

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module

@Module
interface SubModuleBinding<in Activity : AppCompatActivity> {

    @Binds
    fun bindActivity(activity: Activity): AppCompatActivity

}
