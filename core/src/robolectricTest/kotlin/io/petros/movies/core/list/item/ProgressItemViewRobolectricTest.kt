package io.petros.movies.core.list.item

import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.android_test.runner.CustomRobolectricTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isNotNull

@RunWith(CustomRobolectricTestRunner::class)
class ProgressItemViewRobolectricTest {

    @Test
    fun `when the progress item view is instantiated, then layout params is set`() {
        val testedClass = ProgressItemView(context())

        expect { that(testedClass.layoutParams).isNotNull() }
    }

}
