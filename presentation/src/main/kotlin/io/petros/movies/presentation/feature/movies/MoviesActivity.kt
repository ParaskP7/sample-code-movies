package io.petros.movies.presentation.feature.movies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import io.petros.movies.databinding.MoviesActivityBinding
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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Suppress("TooManyFunctions")
class MoviesActivity : BaseActivity(), InfiniteRecyclerView.Listener, MoviesToolbarCallback,
    MovieYearPickerFragmentCallback, MovieMonthPickerFragmentCallback, MovieCallback { // MET

    private val viewModel: MoviesActivityViewModel by viewModel()

    private val moviesNavigator: MoviesNavigator by inject { parametersOf(this) }

    private val adapter = MoviesAdapter()

    @Suppress("LateinitUsage") private lateinit var binding: MoviesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initRecyclerView()
        initObservers()
        loadDataOrRestore()
    }

    private fun initToolbar() {
        binding.toolbar.callback = this
    }

    private fun initRecyclerView() {
        adapter.callback = this
        binding.recyclerView.adapter = adapter
        binding.recyclerView.listener = this
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
        viewModel.loadMoviesOrRestore(binding.toolbar.getYear(), binding.toolbar.getMonth())
    }

    override fun loadData(page: Int?) {
        viewModel.loadMovies(binding.toolbar.getYear(), binding.toolbar.getMonth(), page)
    }

    /* NAVIGATION */

    override fun onClick(movie: SharedElementMovie) {
        moviesNavigator.navigate(movie)
    }

    override fun onErrorClick() {
        loadDataOrRestore()
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
        binding.toolbar.setYear(year)
        binding.toolbar.showMonth()
        viewModel.reloadMovies(year)
    }

    override fun onMonthPicked(month: Int) {
        binding.toolbar.setMonth(month)
        viewModel.reloadMovies(binding.toolbar.getYear(), month)
    }

    /* CONFIGURATION CHANGE */

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.toolbar.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.toolbar.onSaveInstanceState(outState)
    }

    /* CONTRACT */

    override fun constructContentView(): View {
        binding = MoviesActivityBinding.inflate(layoutInflater)
        return binding.root
    }

}
