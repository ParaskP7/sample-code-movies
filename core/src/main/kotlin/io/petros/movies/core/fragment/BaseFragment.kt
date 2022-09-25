@file:Suppress("TypeParameterListSpacing")

package io.petros.movies.core.fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber

@Suppress("TooManyFunctions")
abstract class BaseFragment<
        BINDING : ViewBinding
        >(layout: Int) : Fragment(layout) {

    abstract val binding: BINDING

    /* LIFECYCLE */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.v("Attached. [Context: $context]")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("Created. [Bundle: $savedInstanceState]")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.v("Create view. [Container: $container, Bundle: $savedInstanceState]")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.v("Create view. [View: $view, Bundle: $savedInstanceState]")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Timber.v("Started.")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Timber.v("View state restored. [Bundle: $savedInstanceState]")
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

    override fun onDestroyView() {
        Timber.v("Destroy view.")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.v("Destroyed.")
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        Timber.v("Detached.")
    }

}
