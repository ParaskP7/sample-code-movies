package io.petros.movies.android_test.app

import android.app.Application
import io.petros.movies.android_test.R

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_MaterialComponents)
    }

}
