package io.petros.movies.movies.navigator

import android.app.Activity
import android.content.Intent
import io.petros.movies.movies.MoviesActivity

class MoviesLauncherImpl(
    private val activity: Activity
) : MoviesLauncher {

    override fun launch() {
        activity.startActivity(Intent(activity, MoviesActivity::class.java))
    }

    override fun finish() {
        activity.finish()
    }

}
