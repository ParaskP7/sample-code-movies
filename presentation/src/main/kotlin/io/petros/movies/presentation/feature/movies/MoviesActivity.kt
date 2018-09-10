package io.petros.movies.presentation.feature.movies

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.movies.R
import io.petros.movies.presentation.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : BaseActivity<MoviesActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        loadMovies()
    }

    /* OBSERVERS */

    private fun initObservers() {
        observeMovies()
    }

    private fun observeMovies() {
        viewModel.moviesObservable.observe(this, Observer { it ->
            it?.let { tv_movies.text = it.movies.toString() }
        })
    }

    /* DATA LOADING */

    private fun loadMovies() {
        viewModel.loadMovies()
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movies

    override fun constructViewModel() = MoviesActivityViewModel::class.java

}
