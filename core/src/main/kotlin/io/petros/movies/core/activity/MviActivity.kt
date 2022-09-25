@file:Suppress("TypeParameterListSpacing")

package io.petros.movies.core.activity

import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import io.petros.movies.core.view_model.MviViewModel
import timber.log.Timber

abstract class MviActivity<
        BINDING : ViewBinding,
        INTENT : Any,
        STATE : Any,
        SIDE_EFFECT : Any,
        > : BaseActivity<BINDING>() {

    abstract val viewModel: MviViewModel<INTENT, STATE, SIDE_EFFECT>

    private val state = Observer<STATE> {
        Timber.v("Observed state. [State: $it]")
        renderState(it)
    }

    private val sideEffect = Observer<SIDE_EFFECT> {
        Timber.v("Observed side effect. [Side Effect: $it]")
        renderSideEffect(it)
    }

    override fun onResume() {
        super.onResume()
        initObservers()
    }

    private fun initObservers() {
        viewModel.state().observe(this, state)
        viewModel.sideEffect().observe(this, sideEffect)
    }

    abstract fun renderState(state: STATE)

    abstract fun renderSideEffect(sideEffect: SIDE_EFFECT): Unit?

}
