package io.petros.movies.presentation.feature.movies

import android.os.Bundle
import androidx.lifecycle.Observer
import io.petros.movies.R
import io.petros.movies.presentation.feature.BaseActivity
import io.petros.movies.presentation.feature.common.picker.MovieMonthPickerFragment
import io.petros.movies.presentation.feature.common.picker.MovieMonthPickerFragmentCallback
import io.petros.movies.presentation.feature.common.picker.MovieYearPickerFragment
import io.petros.movies.presentation.feature.common.picker.MovieYearPickerFragmentCallback
import io.petros.movies.presentation.feature.common.toolbar.MoviesToolbarCallback
import io.petros.movies.presentation.feature.common.view.InfiniteRecyclerView
import io.petros.movies.presentation.feature.movies.list.MoviesAdapter
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.navigator.MoviesNavigator
import io.petros.movies.presentation.feature.movies.view.SharedElementMovie
import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Suppress("TooManyFunctions")
class MoviesActivity : BaseActivity(), InfiniteRecyclerView.Listener, MoviesToolbarCallback,
    MovieYearPickerFragmentCallback, MovieMonthPickerFragmentCallback, MovieCallback { // MET

    private val viewModel: MoviesActivityViewModel by viewModel()

    private val moviesNavigator: MoviesNavigator by inject { parametersOf(this) }

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initRecyclerView()
        initObservers()
        loadDataOrRestore()
    }

    private fun initToolbar() {
        toolbar.callback = this
    }

    private fun initRecyclerView() {
        adapter.callback = this
        recycler_view.adapter = adapter
        recycler_view.listener = this
    }

    /* OBSERVERS */

    private fun initObservers() {
        observeStatus()
        observeMovies()
    }

    private fun observeStatus() {
        viewModel.statusObservable.observe(this, Observer { adapterStatus ->
            adapterStatus?.let { adapter.status = it }
        })
    }

    private fun observeMovies() {
        viewModel.moviesObservable.observe(this, Observer { paginationData ->
            paginationData?.let { adapter.setItems(it) }
        })
    }

    /* DATA LOADING */

    override fun loadDataOrRestore() {
        viewModel.loadMoviesOrRestore(toolbar.getYear(), toolbar.getMonth())
    }

    override fun loadData(page: Int?) {
        viewModel.loadMovies(toolbar.getYear(), toolbar.getMonth(), page)
    }

    /* NAVIGATION */

    override fun onClick(movie: SharedElementMovie) {
        moviesNavigator.navigate(movie)
    }

    /* CALLBACK */

    override fun onCloseClicked() {
        viewModel.reloadMovies()
    }

    override fun onYearClicked() {
        MovieYearPickerFragment().show(supportFragmentManager, MovieYearPickerFragment.TAG)
    }

    override fun onMonthClicked() {
        MovieMonthPickerFragment().show(supportFragmentManager, MovieMonthPickerFragment.TAG)
    }

    override fun onYearPicked(year: Int) {
        toolbar.setYear(year)
        toolbar.showMonth()
        viewModel.reloadMovies(year)
    }

    override fun onMonthPicked(month: Int) {
        toolbar.setMonth(month)
        viewModel.reloadMovies(toolbar.getYear(), month)
    }

    /* CONFIGURATION CHANGE */

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        toolbar.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        toolbar.onSaveInstanceState(outState)
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movies

}
