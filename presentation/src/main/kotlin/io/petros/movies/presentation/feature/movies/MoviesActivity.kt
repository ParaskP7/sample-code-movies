package io.petros.movies.presentation.feature.movies

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.movies.R
import io.petros.movies.presentation.feature.BaseActivity
import io.petros.movies.presentation.feature.movies.list.MoviesAdapter
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : BaseActivity<MoviesActivityViewModel>() {

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initObservers()
        loadMovies()
    }

    /* OBSERVERS */

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
    }

    private fun initObservers() {
        observeStatus()
        observeMovies()
    }

    private fun observeStatus() {
        viewModel.statusObservable.observe(this, Observer { it ->
            it?.let { adapter.status = it }
        })
    }

    private fun observeMovies() {
        viewModel.moviesObservable.observe(this, Observer { it ->
            it?.let { adapter.setItems(it.movies) }
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
