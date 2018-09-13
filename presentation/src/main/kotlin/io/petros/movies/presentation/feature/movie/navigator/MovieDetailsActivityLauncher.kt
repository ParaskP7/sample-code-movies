package io.petros.movies.presentation.feature.movie.navigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import io.petros.movies.Henson
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.navigator.ActivityLauncher
import javax.inject.Inject

class MovieDetailsActivityLauncher @Inject constructor(
    private val activity: AppCompatActivity
) : ActivityLauncher(activity), MovieDetailsLauncher {

    override fun launch(movie: Movie) {
        activity.startActivity(getIntent(movie))
    }

    private fun getIntent(movie: Movie): Intent {
        return Henson.with(activity)
            .gotoMovieDetailsActivity()
            .movie(movie)
            .build()
    }

}
