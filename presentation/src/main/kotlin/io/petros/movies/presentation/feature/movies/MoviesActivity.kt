package io.petros.movies.presentation.feature.movies

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.movies.R
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.BaseActivity
import io.petros.movies.presentation.feature.movies.list.MoviesAdapter
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.navigator.MoviesNavigator
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : BaseActivity<MoviesActivityViewModel>(), MovieCallback {

    @Inject lateinit var moviesNavigator: MoviesNavigator

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initObservers()
        loadMovies()
    }

    /* OBSERVERS */

    private fun initRecyclerView() {
        adapter.callback = this
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

    /* NAVIGATION */

    override fun onClick(movie: Movie) {
        moviesNavigator.navigate(movie)
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movies

    override fun constructViewModel() = MoviesActivityViewModel::class.java

}
