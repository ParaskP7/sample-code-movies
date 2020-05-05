package io.petros.movies.core.list

import android.view.View
import io.petros.movies.android_test.app.TestApp
import io.petros.movies.android_test.context.TestContextProvider.context
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import strikt.api.expect
import strikt.assertions.isFalse

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class ProgressViewHolderRobolectricTest {

    @Test
    fun `when the progress view holder is instantiated, then no click listener is set on the item view`() {
        val itemView = View(context())

        ProgressViewHolder(itemView)

        expect { that(itemView.hasOnClickListeners()).isFalse() }
    }

}
