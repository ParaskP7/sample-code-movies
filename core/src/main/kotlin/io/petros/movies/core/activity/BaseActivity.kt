package io.petros.movies.core.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import timber.log.Timber

@Suppress("TooManyFunctions")
abstract class BaseActivity<
        BINDING : ViewBinding
        > : AppCompatActivity() {

    abstract val binding: BINDING

    /* LIFECYCLE */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        constructContentView()?.let { setContentView(it) }
        Timber.v("Created. [Bundle: $savedInstanceState]")
    }

    protected abstract fun constructContentView(): View?

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Timber.v("New intent. [Intent: $intent]")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.v("Restarted.")
    }

    override fun onStart() {
        super.onStart()
        Timber.v("Started.")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.v("Instance state restored. [Bundle: $savedInstanceState]")
    }

    override fun onResume() {
        super.onResume()
        Timber.v("Resumed.")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.v("New config. [Config: $newConfig]")
    }

    override fun onPause() {
        Timber.v("Paused.")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.v("Instance state saved. [Bundle: %s]", outState)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Timber.v("Stopped.")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.v("Destroyed.")
        super.onDestroy()
    }

}
