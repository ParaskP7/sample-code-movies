package io.petros.movies.presentation.feature.movies

import io.petros.movies.R
import io.petros.movies.presentation.feature.BaseActivity

class MoviesActivity : BaseActivity<MoviesActivityViewModel>() {

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movies

    override fun constructViewModel() = MoviesActivityViewModel::class.java

}
