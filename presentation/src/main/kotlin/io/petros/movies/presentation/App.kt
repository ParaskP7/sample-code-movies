package io.petros.movies.presentation

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ProcessLifecycleOwner
import android.os.Build
import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import io.petros.movies.BuildConfig
import io.petros.movies.R
import timber.log.Timber

@Suppress("TooManyFunctions")
class App : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        if (!initLeakCanary()) return
        initLogging()
        initStrictMode()
        initLifecycleObserver()
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

    private fun initStrictMode() {
        if (BuildConfig.DEBUG) {
            setThreadPolicyToStrictMode()
            setVmPolicyToStrictMode()
            Timber.v("${javaClass.simpleName} strict mode initialised.")
        }
    }

    private fun setThreadPolicyToStrictMode() {
        val strictModeBuilder = StrictMode.ThreadPolicy.Builder()
            .detectDiskReads()
            .detectDiskWrites()
            .detectNetwork()
            .detectCustomSlowCalls()
            .penaltyLog()
            .penaltyFlashScreen()
            .penaltyDialog()
        addAndroidMThreadPolicyToStrictMode(strictModeBuilder)
        addAndroidOThreadPolicyToStrictMode(strictModeBuilder)
        StrictMode.setThreadPolicy(strictModeBuilder.build())
    }

    private fun addAndroidMThreadPolicyToStrictMode(strictModeBuilder: StrictMode.ThreadPolicy.Builder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            strictModeBuilder.detectResourceMismatches()
        }
    }

    private fun addAndroidOThreadPolicyToStrictMode(strictModeBuilder: StrictMode.ThreadPolicy.Builder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            strictModeBuilder.detectUnbufferedIo()
        }
    }

    private fun setVmPolicyToStrictMode() {
        val strictModeBuilder = StrictMode.VmPolicy.Builder()
            .detectActivityLeaks()
            .detectFileUriExposure()
            .detectLeakedClosableObjects()
            .detectLeakedSqlLiteObjects()
            .detectLeakedRegistrationObjects()
            .penaltyLog()
        addAndroidOVmPolicyToStrictMode(strictModeBuilder)
        StrictMode.setVmPolicy(strictModeBuilder.build())
    }

    private fun addAndroidOVmPolicyToStrictMode(strictModeBuilder: StrictMode.VmPolicy.Builder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            strictModeBuilder.detectCleartextNetwork()
                .detectContentUriWithoutPermission()
        }
    }

    private fun initLifecycleObserver() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    /* LIFECYCLE */

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        Timber.v("${javaClass.simpleName} created.")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Timber.v("${javaClass.simpleName} started. [App In Foreground]")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Timber.v("${javaClass.simpleName} resumed.")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Timber.v("${javaClass.simpleName} paused.")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Timber.v("${javaClass.simpleName} stopped. [App In Background]")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyed() {
        Timber.v("${javaClass.simpleName} destroyed.")
    }

}
