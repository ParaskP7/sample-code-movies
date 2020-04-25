package io.petros.movies.android_test.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import io.petros.movies.android_test.app.InstrumentedTestApp

@Suppress("unused")
class CustomAndroidJunitRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application =
        super.newApplication(cl, InstrumentedTestApp::class.java.name, context)

}
