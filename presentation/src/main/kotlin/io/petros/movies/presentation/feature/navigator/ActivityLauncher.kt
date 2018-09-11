package io.petros.movies.presentation.feature.navigator

import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

open class ActivityLauncher @Inject constructor(
    private val activity: AppCompatActivity
) : Launcher {

    override fun finish() {
        activity.finish()
    }

}
