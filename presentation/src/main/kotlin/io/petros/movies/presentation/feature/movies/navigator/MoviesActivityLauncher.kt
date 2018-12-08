package io.petros.movies.presentation.feature.movies.navigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.petros.movies.presentation.feature.movies.MoviesActivity
import io.petros.movies.presentation.feature.navigator.ActivityLauncher
import javax.inject.Inject

class MoviesActivityLauncher @Inject constructor(
    private val activity: AppCompatActivity
) : ActivityLauncher(activity), MoviesLauncher {

    override fun launch() {
        activity.startActivity(Intent(activity, MoviesActivity::class.java))
    }

}
