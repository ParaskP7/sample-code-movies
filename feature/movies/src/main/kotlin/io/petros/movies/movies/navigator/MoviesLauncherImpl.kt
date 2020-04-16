package io.petros.movies.movies.navigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.movies.MoviesActivity

class MoviesLauncherImpl(
    private val activity: AppCompatActivity
) : MoviesLauncher {

    override fun launch() {
        activity.startActivity(Intent(activity, MoviesActivity::class.java))
    }

    override fun finish() {
        activity.finish()
    }

}
