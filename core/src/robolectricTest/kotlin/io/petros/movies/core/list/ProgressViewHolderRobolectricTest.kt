package io.petros.movies.core.list

import android.view.View
import io.petros.movies.android_test.context.TestContextProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import strikt.api.expect
import strikt.assertions.isFalse

@RunWith(RobolectricTestRunner::class)
class ProgressViewHolderRobolectricTest {

    @Test
    fun `when the progress view holder is instantiated, then no click listener is set on the item view`() {
        val itemView = View(TestContextProvider.context())

        ProgressViewHolder(itemView)

        expect { that(itemView.hasOnClickListeners()).isFalse() }
    }

}
