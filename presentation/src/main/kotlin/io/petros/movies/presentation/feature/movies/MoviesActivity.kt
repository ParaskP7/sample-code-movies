package io.petros.movies.presentation.feature.movies

import android.arch.lifecycle.Observer
import android.os.Bundle
import io.petros.movies.R
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.BaseActivity
import io.petros.movies.presentation.feature.common.picker.MovieYearPickerFragment
import io.petros.movies.presentation.feature.common.picker.MovieYearPickerFragmentCallback
import io.petros.movies.presentation.feature.common.toolbar.MoviesToolbarCallback
import io.petros.movies.presentation.feature.movies.list.MoviesAdapter
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.navigator.MoviesNavigator
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

@Suppress("TooManyFunctions")
class MoviesActivity : BaseActivity<MoviesActivityViewModel>(), MoviesToolbarCallback,
    MovieYearPickerFragmentCallback, MovieCallback {

    @Inject lateinit var moviesNavigator: MoviesNavigator

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initRecyclerView()
        initObservers()
        loadMovies()
    }

    private fun initToolbar() {
        toolbar.callback = this
    }

    private fun initRecyclerView() {
        adapter.callback = this
        recycler_view.adapter = adapter
    }

    /* OBSERVERS */

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

    /* CALLBACK */

    override fun onYearClicked() {
        MovieYearPickerFragment().show(supportFragmentManager, MovieYearPickerFragment.TAG)
    }

    override fun onYearPicked(year: Int) {
        toolbar.setYear(year)
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movies

    override fun constructViewModel() = MoviesActivityViewModel::class.java

}
