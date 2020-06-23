package io.petros.movies.core.list

import android.view.View
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.android_test.runner.CustomRobolectricTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isFalse

@RunWith(CustomRobolectricTestRunner::class)
class ProgressViewHolderRobolectricTest {

    @Test
    fun `when the progress view holder is instantiated, then no click listener is set on the item view`() {
        val itemView = View(context())

        ProgressViewHolder(itemView)

        expect { that(itemView.hasOnClickListeners()).isFalse() }
    }

}
