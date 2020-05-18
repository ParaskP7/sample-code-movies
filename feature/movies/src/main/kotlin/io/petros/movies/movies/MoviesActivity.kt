package io.petros.movies.movies

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.core.activity.MviActivity
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.core.list.infinite.InfiniteRecyclerView
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.domain.model.movie.MoviesStatus
import io.petros.movies.movies.databinding.MoviesActivityBinding
import io.petros.movies.movies.list.MoviesAdapter
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.navigator.MoviesNavigator
import io.petros.movies.movies.toolbar.MoviesToolbarCallback
import io.petros.movies.picker.MovieMonthPickerFragment
import io.petros.movies.picker.MovieYearPickerFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Suppress("TooManyFunctions")
class MoviesActivity : MviActivity<MoviesIntent, MoviesState, MoviesSideEffect, MoviesViewModel>(),
    MoviesToolbarCallback,
    MovieItemCallback,
    InfiniteRecyclerView.Listener {

    companion object {

        private const val INSTANCE_STATE_KEY_RELOAD_ITEMS = "RELOAD_ITEMS"

    }

    override val viewModel: MoviesViewModel by viewModel()

    private val moviesNavigator: MoviesNavigator by inject { parametersOf(this) }

    private val adapter = MoviesAdapter()

    private var snackbar: Snackbar? = null

    private var reloadItems = false

    private val binding by viewBinding(MoviesActivityBinding::inflate)

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

    override fun onResume() {
        super.onResume()
        viewModel.process(MoviesIntent.IdleMovies)
    }

    /* STATE */

    override fun renderState(state: MoviesState) = when (state.status) {
        is MoviesStatus.Init -> renderInitState()
        is MoviesStatus.Idle -> renderIdleState(state)
        is MoviesStatus.Loading -> renderLoadingState()
        is MoviesStatus.Loaded -> renderLoadedState(state)
    }

    private fun renderInitState() {
        viewModel.process(
            MoviesIntent.LoadMovies(
                year = binding.toolbar.getYear(),
                month = binding.toolbar.getMonth()
            )
        )
    }

    private fun renderIdleState(state: MoviesState) {
        state.year?.let { year ->
            binding.toolbar.showCloseIcon()
            binding.toolbar.restoreYear(year)
            state.month?.let { binding.toolbar.restoreMonth(it) }
        }
        adapter.status = AdapterStatus.IDLE
        adapter.setItems(state.movies, true)
    }

    private fun renderLoadingState() {
        adapter.status = AdapterStatus.LOADING
        snackbar?.dismiss()
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

    private fun renderErrorSideEffect() {
        snackbar = Snackbar
            .make(binding.ctrMovies, R.string.sbLoadMoviesError, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.sbLoadMoviesErrorAction) {
                viewModel.process(
                    MoviesIntent.LoadMovies(
                        year = binding.toolbar.getYear(),
                        month = binding.toolbar.getMonth(),
                        page = adapter.nextPage()
                    )
                )
            }
        snackbar?.show()
    }

    /* DATA LOADING */

    override fun loadData(page: Int?) {
        viewModel.process(
            MoviesIntent.LoadMovies(
                year = binding.toolbar.getYear(),
                month = binding.toolbar.getMonth(),
                page = page
            )
        )
    }

    /* NAVIGATION */

    override fun onClick(movie: Movie) {
        moviesNavigator.navigate(movie)
    }

    /* CALLBACK */

    override fun onCloseClicked() {
        viewModel.process(
            MoviesIntent.ReloadMovies()
        )
    }

    override fun onYearClicked() {
        MovieYearPickerFragment { year -> onYearPicked(year) }
            .show(supportFragmentManager)
    }

    private fun onYearPicked(year: Int) {
        binding.toolbar.setYear(year)
        binding.toolbar.showMonth()
        viewModel.process(
            MoviesIntent.ReloadMovies(
                year = year
            )
        )
    }

    override fun onMonthClicked() {
        MovieMonthPickerFragment { month -> onMonthPicked(month) }
            .show(supportFragmentManager)
    }

    private fun onMonthPicked(month: Int) {
        binding.toolbar.setMonth(month)
        viewModel.process(
            MoviesIntent.ReloadMovies(
                year = binding.toolbar.getYear(),
                month = month
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

    override fun constructContentView() = binding.root

}
