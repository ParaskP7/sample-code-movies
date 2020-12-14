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
        Timber.v("${javaClass.simpleName} created. [Bundle: $savedInstanceState]")
    }

    protected abstract fun constructContentView(): View?

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Timber.v("${javaClass.simpleName} new intent. [Intent: $intent]")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.v("${javaClass.simpleName} restarted.")
    }

    override fun onStart() {
        super.onStart()
        Timber.v("${javaClass.simpleName} started.")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.v("${javaClass.simpleName} instance state restored. [Bundle: $savedInstanceState]")
    }

    override fun onResume() {
        super.onResume()
        Timber.v("${javaClass.simpleName} resumed.")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.v("${javaClass.simpleName} new config. [Config: $newConfig]")
    }

    override fun onPause() {
        Timber.v("${javaClass.simpleName} paused.")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.v("${javaClass.simpleName} instance state saved. [Bundle: %s]", outState)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Timber.v("${javaClass.simpleName} stopped.")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.v("${javaClass.simpleName} destroyed.")
        super.onDestroy()
    }

}
