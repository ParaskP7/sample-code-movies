package io.petros.movies.presentation.feature.movie.navigator

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import io.petros.movies.Henson
import io.petros.movies.R
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie
import io.petros.movies.presentation.feature.navigator.ActivityLauncher
import javax.inject.Inject

class MovieDetailsActivityLauncher @Inject constructor(
    private val activity: AppCompatActivity
) : ActivityLauncher(activity), MovieDetailsLauncher {

    override fun launch(movie: Movie) {
        activity.startActivity(getIntent(movie))
    }

    override fun launch(movie: SharedElementMovie) {
        activity.startActivity(getIntent(movie.movie), getSharedElement(movie))
    }

    private fun getIntent(movie: Movie): Intent {
        return Henson.with(activity)
            .gotoMovieDetailsActivity()
            .movie(movie)
            .build()
    }

    private fun getSharedElement(movie: SharedElementMovie): Bundle? {
        val sharedElement = activity.getString(R.string.movie_backdrop_shared_element)
        val anim = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, movie.sharedElement, sharedElement)
        return anim.toBundle()
    }

}
