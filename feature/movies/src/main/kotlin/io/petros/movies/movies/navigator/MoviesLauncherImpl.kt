package io.petros.movies.movies.navigator

import android.app.Activity
import android.content.Intent
import io.petros.movies.movies.MoviesFragment

class MoviesLauncherImpl(
    private val activity: Activity
) : MoviesLauncher {

    override fun launch() {
        activity.startActivity(Intent(activity, MoviesFragment::class.java))
    }

    override fun finish() {
        activity.finish()
    }

}
