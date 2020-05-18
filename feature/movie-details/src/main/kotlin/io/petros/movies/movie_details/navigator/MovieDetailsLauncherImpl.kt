package io.petros.movies.movie_details.navigator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import io.petros.movies.android_utils.getExtraName
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movie_details.MovieDetailsFragment

class MovieDetailsLauncherImpl(
    private val activity: Activity
) : MovieDetailsLauncher {

    companion object {

        private val EXTRA_CLASS = MovieDetailsFragment::class.java
        private val EXTRA_MOVIE = getExtraName(EXTRA_CLASS, "movie")

        @Suppress("UnsafeCast")
        @SuppressLint("SyntheticAccessor")
        fun getMovie(intent: Intent) = intent.getSerializableExtra(EXTRA_MOVIE) as Movie

    }

    override fun launch(movie: Movie) {
        activity.startActivity(getIntent(movie))
    }

    private fun getIntent(movie: Movie): Intent {
        return Intent(activity, EXTRA_CLASS).also {
            it.putExtra(EXTRA_MOVIE, movie)
        }
    }

    override fun finish() {
        activity.finish()
    }

}
