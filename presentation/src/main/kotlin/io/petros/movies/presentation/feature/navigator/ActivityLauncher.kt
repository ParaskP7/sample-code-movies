package io.petros.movies.presentation.feature.navigator

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

open class ActivityLauncher @Inject constructor(
    private val activity: AppCompatActivity
) : Launcher {

    override fun finish() {
        activity.finish()
    }

}
