package io.petros.movies.movies

import android.os.Bundle
import android.view.View
import io.petros.movies.core.activity.MviActivity
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.core.list.infinite.InfiniteRecyclerView
import io.petros.movies.domain.model.movie.MoviesStatus
import io.petros.movies.movie_details.navigator.SharedElementMovie
import io.petros.movies.movies.databinding.MoviesActivityBinding
import io.petros.movies.movies.list.MoviesAdapter
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.navigator.MoviesNavigator
import io.petros.movies.movies.toolbar.MoviesToolbarCallback
import io.petros.movies.picker.MovieMonthPickerFragment
import io.petros.movies.picker.MovieMonthPickerFragmentCallback
import io.petros.movies.picker.MovieYearPickerFragment
import io.petros.movies.picker.MovieYearPickerFragmentCallback
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Suppress("TooManyFunctions")
class MoviesActivity : MviActivity<MoviesIntent, MoviesState, MoviesSideEffect, MoviesViewModel>(),
    MoviesToolbarCallback,
    MovieItemCallback,
    InfiniteRecyclerView.Listener,
    MovieYearPickerFragmentCallback,
    MovieMonthPickerFragmentCallback {

    companion object {

        private const val INSTANCE_STATE_KEY_RELOAD_ITEMS = "RELOAD_ITEMS"

    }

    override val viewModel: MoviesViewModel by viewModel()

    private val moviesNavigator: MoviesNavigator by inject { parametersOf(this) }

    private val adapter = MoviesAdapter()

    private var reloadItems = false

    @Suppress("LateinitUsage") private lateinit var binding: MoviesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        binding.toolbar.callback = this
    }

    private fun initRecyclerView() {
        adapter.itemCallback = this
        binding.recyclerView.adapter = adapter
        binding.recyclerView.listener = this
    }

    /* STATE */

    override fun renderState(state: MoviesState) = when (state.status) {
        is MoviesStatus.Init -> renderInitState()
        is MoviesStatus.Loading -> renderLoadingState()
        is MoviesStatus.Loaded -> renderLoadedState(state)
    }

    private fun renderInitState() {
        viewModel.process(
            MoviesIntent.LoadMovies(
                binding.toolbar.getYear(),
                binding.toolbar.getMonth()
            )
        )
    }

    private fun renderLoadingState() {
        adapter.status = AdapterStatus.LOADING
    }

    private fun renderLoadedState(state: MoviesState) {
        adapter.status = AdapterStatus.IDLE
        adapter.setItems(state.movies, reloadItems)
        reloadItems = false
    }

    /* SIDE EFFECT */

    override fun renderSideEffect(sideEffect: MoviesSideEffect) = when (sideEffect) {
        is MoviesSideEffect.Error -> renderErrorSideEffect()
    }

    @Suppress("ForbiddenComment")
    private fun renderErrorSideEffect() {
        // TODO: Implement side effect
    }

    /* DATA LOADING */

    override fun loadData(page: Int?) {
        viewModel.process(
            MoviesIntent.LoadMovies(
                binding.toolbar.getYear(),
                binding.toolbar.getMonth(),
                page
            )
        )
    }

    /* NAVIGATION */

    override fun onClick(movie: SharedElementMovie) {
        moviesNavigator.navigate(movie)
    }

    /* CALLBACK */

    override fun onCloseClicked() {
        viewModel.process(
            MoviesIntent.ReloadMovies()
        )
    }

    override fun onYearClicked() {
        MovieYearPickerFragment().show(supportFragmentManager)
    }

    override fun onMonthClicked() {
        MovieMonthPickerFragment().show(supportFragmentManager)
    }

    override fun onYearPicked(year: Int) {
        binding.toolbar.setYear(year)
        binding.toolbar.showMonth()
        viewModel.process(
            MoviesIntent.ReloadMovies(
                year
            )
        )
    }

    override fun onMonthPicked(month: Int) {
        binding.toolbar.setMonth(month)
        viewModel.process(
            MoviesIntent.ReloadMovies(
                binding.toolbar.getYear(),
                month
            )
        )
    }

    /* CONFIGURATION CHANGE */

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.toolbar.onRestoreInstanceState(savedInstanceState)
        onRestoreDoReloadInstanceState(savedInstanceState)
    }

    private fun onRestoreDoReloadInstanceState(savedInstanceState: Bundle) {
        reloadItems = savedInstanceState.getBoolean(INSTANCE_STATE_KEY_RELOAD_ITEMS)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.toolbar.onSaveInstanceState(outState)
        onSaveDoReloadInstanceState(outState)
    }

    private fun onSaveDoReloadInstanceState(outState: Bundle) {
        outState.putBoolean(INSTANCE_STATE_KEY_RELOAD_ITEMS, true)
    }

    /* CONTRACT */

    override fun constructContentView(): View {
        binding = MoviesActivityBinding.inflate(layoutInflater)
        return binding.root
    }

}
