package io.petros.movies.presentation.feature

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

@Suppress("TooManyFunctions")
abstract class BaseActivity : AppCompatActivity() {

    /* LIFECYCLE */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        Timber.v("${javaClass.simpleName} created. [Bundle: $savedInstanceState]")
    }

    private fun setContentView() {
        constructContentView()?.let { setContentView(it) }
    }

    protected abstract fun constructContentView(): Int?

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Timber.v("${javaClass.simpleName} new intent. [Intent: $intent]")
    }

    public override fun onRestart() {
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

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Timber.v("${javaClass.simpleName} new config. [Config: $newConfig]")
    }

    override fun onPause() {
        Timber.v("${javaClass.simpleName} paused.")
        super.onPause()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
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
