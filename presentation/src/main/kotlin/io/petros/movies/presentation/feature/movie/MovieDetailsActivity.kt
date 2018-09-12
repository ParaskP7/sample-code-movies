package io.petros.movies.presentation.feature.movie

import io.petros.movies.R
import io.petros.movies.presentation.feature.BaseActivity

class MovieDetailsActivity : BaseActivity<MovieDetailsActivityViewModel>() {

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movie_details

    override fun constructViewModel() = MovieDetailsActivityViewModel::class.java

}
