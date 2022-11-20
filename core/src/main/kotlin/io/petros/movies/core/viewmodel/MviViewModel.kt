@file:Suppress("TypeParameterListSpacing")

package io.petros.movies.core.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.petros.movies.androidutils.SingleLiveEvent
import timber.log.Timber

abstract class MviViewModel<
        INTENT : Any,
        STATE : Any,
        SIDE_EFFECT : Any,
        > : ViewModel() {

    private val _stateLiveData: MutableLiveData<STATE> = MutableLiveData()
    private var _state: STATE? = null
    protected var state: STATE
        get() = _state
            ?: throw UninitializedPropertyAccessException("'state' was queried before being initialized.")
        set(value) {
            Timber.v("Setting state. [State: $value]")
            _state = value
            _stateLiveData.value = value
        }

    private val _sideEffectLiveData: SingleLiveEvent<SIDE_EFFECT> = SingleLiveEvent()
    private var _sideEffect: SIDE_EFFECT? = null
    protected var sideEffect: SIDE_EFFECT
        get() = _sideEffect
            ?: throw UninitializedPropertyAccessException("'sideEffect' was queried before being initialized.")
        set(value) {
            Timber.v("Setting side effect. [Side Effect: $value]")
            _sideEffect = value
            _sideEffectLiveData.value = value
        }

    @CallSuper
    open fun process(intent: INTENT) {
        if (!state().hasObservers()) throw NoObserverAttachedException("No observer attached.")
        Timber.d("Processing intent. [Intent: $intent]")
    }

    fun state(): LiveData<STATE> = _stateLiveData

    fun sideEffect(): SingleLiveEvent<SIDE_EFFECT> = _sideEffectLiveData

    override fun onCleared() {
        Timber.v("Cleared.")
        super.onCleared()
    }

}

class NoObserverAttachedException(message: String) : Exception(message)
