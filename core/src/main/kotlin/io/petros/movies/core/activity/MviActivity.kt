package io.petros.movies.core.activity

import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import dev.fanie.stateful.StatefulInstance
import io.petros.movies.core.view_model.MviViewModel
import timber.log.Timber

abstract class MviActivity<
        BINDING : ViewBinding,
        INTENT : Any,
        STATE : Any,
        SIDE_EFFECT : Any,
        > : BaseActivity<BINDING>() {

    abstract val viewModel: MviViewModel<INTENT, STATE, SIDE_EFFECT>
    abstract val stateful: StatefulInstance<STATE>

    private val state = Observer<STATE> {
        Timber.v("${javaClass.simpleName} observed state. [State: $it]")
        stateful.accept(it)
    }

    private val sideEffect = Observer<SIDE_EFFECT> {
        Timber.v("${javaClass.simpleName} observed side effect. [Side Effect: $it]")
        renderSideEffect(it)
    }

    override fun onResume() {
        super.onResume()
        stateful.clear()
        initObservers()
    }

    private fun initObservers() {
        viewModel.state().observe(this, state)
        viewModel.sideEffect().observe(this, sideEffect)
    }

    abstract fun renderSideEffect(sideEffect: SIDE_EFFECT): Unit?

}
