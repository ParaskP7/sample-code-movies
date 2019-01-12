package io.petros.movies.presentation.feature.movies.navigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.presentation.feature.movies.MoviesActivity

class MoviesActivityLauncher constructor(
    private val activity: AppCompatActivity
) : MoviesLauncher {

    override fun launch() {
        activity.startActivity(Intent(activity, MoviesActivity::class.java))
    }

    override fun finish() {
        activity.finish()
    }

}
