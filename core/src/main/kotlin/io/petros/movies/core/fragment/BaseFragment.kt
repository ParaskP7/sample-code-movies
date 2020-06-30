package io.petros.movies.core.fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber

@Suppress("TooManyFunctions")
abstract class BaseFragment<
        BINDING : Any
        >(layout: Int) : Fragment(layout) {

    abstract val binding: BINDING

    /* LIFECYCLE */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.v("${javaClass.simpleName} attached. [Context: $context]")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("${javaClass.simpleName} created. [Bundle: $savedInstanceState]")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.v("${javaClass.simpleName} create view. [Container: $container, Bundle: $savedInstanceState]")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.v("${javaClass.simpleName} create view. [View: $view, Bundle: $savedInstanceState]")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Timber.v("${javaClass.simpleName} started.")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Timber.v("${javaClass.simpleName} view state restored. [Bundle: $savedInstanceState]")
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

    override fun onDestroyView() {
        Timber.v("${javaClass.simpleName} destroy view.")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.v("${javaClass.simpleName} destroyed.")
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        Timber.v("${javaClass.simpleName} detached.")
    }

}
