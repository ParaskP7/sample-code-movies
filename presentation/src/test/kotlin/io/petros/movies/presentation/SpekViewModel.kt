package io.petros.movies.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

abstract class ViewModelSpek(val viewModelRoot: Root.() -> Unit) : Spek({
    instantTaskExecutorRule()
    viewModelRoot()
})

private fun Root.instantTaskExecutorRule() {
    beforeEachTest { ArchTaskExecutor.getInstance().setDelegate(taskExecutor()) }
    afterEachTest { ArchTaskExecutor.getInstance().setDelegate(null) }
}

private fun taskExecutor() = object : TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean {
        return true
    }
}
