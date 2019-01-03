package io.petros.movies.presentation.feature

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import io.petros.movies.presentation.permitDiskReads
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Suppress("UnsafeCast")
open class BaseViewModel constructor(
    var job: Job = Job(),
    var uiScope: CoroutineScope = permitDiskReads { CoroutineScope(Dispatchers.Main + job) } as CoroutineScope
) : ViewModel() {

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
