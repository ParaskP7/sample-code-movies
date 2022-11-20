package io.petros.movies.androidtest.app

import android.app.Application
import io.petros.movies.android.test.R

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_MaterialComponents)
    }

}
