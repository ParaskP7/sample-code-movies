package io.petros.movies.app.robot.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import io.petros.movies.android_test.robot.actions.Actions
import io.petros.movies.android_test.robot.actions.ScopedActions
import io.petros.movies.android_test.robot.utils.RecyclerViewMatcher
import io.petros.movies.android_test.robot.utils.idMatcher
import io.petros.movies.feature.movies.R
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class MoviesListRobot(
    private val matcher: () -> Matcher<View> = idMatcher(R.id.recyclerView)
) : ScopedActions(matcher) {

    fun inItem(position: Int, action: MovieItemRobot.() -> Actions): Actions {
        onView(matcher())
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position - 1))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position + 1))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        return MovieItemRobot(positionMatcher(position)).action()
    }

    private fun positionMatcher(position: Int): () -> TypeSafeMatcher<View> =
        { RecyclerViewMatcher(R.id.recyclerView).atPosition(position) }

}
