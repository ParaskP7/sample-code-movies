package io.petros.movies.test.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

@ExperimentalCoroutinesApi
abstract class CoroutineSpek(
    val testRoot: Root.() -> Unit,
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher(),
) : Spek({
    coroutinesTestRule(dispatcher)
    testRoot()
})

@ExperimentalCoroutinesApi
private fun Root.coroutinesTestRule(testDispatcher: TestCoroutineDispatcher) {
    beforeEachTest { Dispatchers.setMain(testDispatcher) }
    afterEachTest {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
