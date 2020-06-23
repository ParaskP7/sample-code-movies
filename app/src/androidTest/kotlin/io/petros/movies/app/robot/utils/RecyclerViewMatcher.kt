package io.petros.movies.app.robot.utils

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int) = atPositionOnView(position, -1)

    @Suppress("SwallowedException", "SameParameterValue")
    private fun atPositionOnView(position: Int, targetViewId: Int) = object : TypeSafeMatcher<View>() {
        var resources: Resources? = null
        var childView: View? = null

        override fun describeTo(description: Description) {
            var idDescription = recyclerViewId.toString()
            if (this.resources != null) {
                idDescription = try {
                    this.resources!!.getResourceName(recyclerViewId)
                } catch (var4: Resources.NotFoundException) {
                    "$recyclerViewId (resource name not found)"
                }
            }

            description.appendText("RecyclerView with id: $idDescription at position: $position")
        }

        public override fun matchesSafely(view: View): Boolean {
            this.resources = view.resources

            if (childView == null) {
                val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                if (recyclerView?.id == recyclerViewId) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                    childView = viewHolder?.itemView
                } else {
                    return false
                }
            }

            return if (targetViewId == -1) {
                view === childView
            } else {
                val targetView = childView?.findViewById<View>(targetViewId)
                view === targetView
            }
        }
    }

}
