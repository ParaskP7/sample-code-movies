package io.petros.movies.presentation.di.dagger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST", "NestedBlockDepth", "SwallowedException")
    override fun <Modeling : ViewModel> create(modelClass: Class<Modeling>): Modeling {
        var creator: Provider<ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        if (creator == null) throw IllegalArgumentException("Unknown model class. [Model Class: $modelClass]")

        return try {
            creator.get() as Modeling
        } catch (cce: ClassCastException) {
            throw ClassCastException("The injector encountered an error while providing an instance.")
        }
    }

}
