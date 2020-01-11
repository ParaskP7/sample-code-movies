package io.petros.movies.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

@ExperimentalCoroutinesApi
abstract class ViewModelSpek(
    val viewModelRoot: Root.() -> Unit,
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : Spek({
    coroutinesTestRule(dispatcher)
    instantTaskExecutorRule()
    viewModelRoot()
})

@ExperimentalCoroutinesApi
private fun Root.coroutinesTestRule(testDispatcher: TestCoroutineDispatcher) {
    beforeEachTest { Dispatchers.setMain(testDispatcher) }
    afterEachTest {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

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
