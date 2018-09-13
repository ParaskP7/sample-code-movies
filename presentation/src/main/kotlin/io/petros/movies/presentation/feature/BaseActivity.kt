package io.petros.movies.presentation.feature

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.f2prateek.dart.Dart
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

@Suppress("TooManyFunctions")
abstract class BaseActivity<Modeling : ViewModel> : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: Modeling

    /* LIFECYCLE */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dart.inject(this)
        setContentView()
        setViewModel()
        Timber.v("${javaClass.simpleName} created. [Bundle: $savedInstanceState]")
    }

    private fun setContentView() {
        constructContentView()?.let { setContentView(it) }
    }

    protected abstract fun constructContentView(): Int?

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(constructViewModel())
        Timber.v("${javaClass.simpleName} view model constructed.")
    }

    protected abstract fun constructViewModel(): Class<Modeling>

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
