package io.petros.movies.presentation

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (!initLeakCanary()) return
    }

    private fun initLeakCanary(): Boolean {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            false
        } else {
            LeakCanary.install(this)
            true
        }
    }

}
