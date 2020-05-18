package io.petros.movies.core.activity

import androidx.lifecycle.Observer
import io.petros.movies.core.view_model.MviViewModel
import timber.log.Timber

abstract class MviActivity<
        INTENT : Any,
        STATE : Any,
        SIDE_EFFECT : Any,
        VIEW_MODEL : MviViewModel<INTENT, STATE, SIDE_EFFECT>
        > : BaseActivity() {

    abstract val viewModel: VIEW_MODEL

    private val state = Observer<STATE> {
        Timber.v("${javaClass.simpleName} observed state. [State: $it]")
        renderState(it)
    }

    private val sideEffect = Observer<SIDE_EFFECT> {
        Timber.v("${javaClass.simpleName} observed side effect. [Side Effect: $it]")
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
