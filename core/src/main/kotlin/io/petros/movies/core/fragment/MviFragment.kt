@file:Suppress("TypeParameterListSpacing")

package io.petros.movies.core.fragment

import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import io.petros.movies.core.viewmodel.MviViewModel
import timber.log.Timber

abstract class MviFragment<
        BINDING : ViewBinding,
        INTENT : Any,
        STATE : Any,
        SIDE_EFFECT : Any,
        >(layout: Int) : BaseFragment<BINDING>(layout) {

    abstract val viewModel: MviViewModel<INTENT, STATE, SIDE_EFFECT>

    private val state = Observer<STATE> { state ->
        Timber.v("Observed state. [State: $state]")
        renderState(state)
    }

    private val sideEffect = Observer<SIDE_EFFECT> { sideEffect ->
        Timber.v("Observed side effect. [Side Effect: $sideEffect]")
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
