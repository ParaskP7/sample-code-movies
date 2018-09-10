package io.petros.movies.presentation

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.petros.movies.BuildConfig
import io.petros.movies.R
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (!initLeakCanary()) return
        initLogging()
        Timber.i("${getString(R.string.app_name)} created.")
    }

    private fun initLeakCanary(): Boolean {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            false
        } else {
            LeakCanary.install(this)
            true
        }
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.v("${javaClass.simpleName} logging initialised.")
        }
    }

}
