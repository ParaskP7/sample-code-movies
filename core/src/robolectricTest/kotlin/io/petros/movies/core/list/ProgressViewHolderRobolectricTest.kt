package io.petros.movies.core.list

import android.view.View
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProgressViewHolderRobolectricTest {

    @Test
    fun `when the progress view holder is instantiated, then no click listener is set on the item view`() {
        val itemViewMock = mockk<View>()

        ProgressViewHolder(itemViewMock)

        verify(exactly = 0) { itemViewMock.setOnClickListener(any()) }
    }

}
