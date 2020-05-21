package io.petros.movies.movies

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.core.fragment.MviFragment
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.core.list.infinite.InfiniteRecyclerView
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movies.databinding.MoviesFragmentBinding
import io.petros.movies.movies.list.MoviesAdapter
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.toolbar.MoviesToolbarCallback
import io.petros.movies.picker.MovieMonthPickerFragment
import io.petros.movies.picker.MovieYearPickerFragment
import io.petros.movies.utils.slash
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("TooManyFunctions")
class MoviesFragment : MviFragment<MoviesIntent, MoviesState, MoviesSideEffect, MoviesViewModel>(R.layout.movies_fragment),
    MoviesToolbarCallback,
    MovieItemCallback,
    InfiniteRecyclerView.Listener {

    companion object {

        private const val INSTANCE_STATE_KEY_RELOAD_ITEMS = "RELOAD_ITEMS"

        private const val MOVIE_DETAILS_DEEP_LINK = "io.petros.movies://movieDetailsFragment"

    }

    private val binding by viewBinding(MoviesFragmentBinding::bind)
    override val viewModel: MoviesViewModel by viewModel()

    private var adapter: MoviesAdapter? = null
    private var snackbar: Snackbar? = null

    private var reloadItems = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        binding.toolbar.callback = this
    }

    private fun initRecyclerView() {
        adapter = MoviesAdapter()
        adapter?.itemCallback = this
        binding.recyclerView.adapter = adapter
        binding.recyclerView.listener = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.process(MoviesIntent.IdleMovies)
    }

    override fun onDestroyView() {
        adapter = null
        snackbar = null
        super.onDestroyView()
    }

    /* STATE */

    override fun renderState(state: MoviesState) = when (state.status) {
        is MoviesStatus.Init -> renderInitState()
        is MoviesStatus.Idle -> renderIdleState(state)
        is MoviesStatus.Loading -> renderLoadingState()
        is MoviesStatus.Loaded -> renderLoadedState(state)
    }

    private fun renderInitState() {
        viewModel.process(MoviesIntent.LoadMovies())
    }

    private fun renderIdleState(state: MoviesState) {
        state.year?.let { year ->
            binding.toolbar.showCloseIcon()
            binding.toolbar.restoreYear(year)
            state.month?.let { binding.toolbar.restoreMonth(it) }
        }
        adapter?.status = AdapterStatus.IDLE
        adapter?.setItems(state.movies, true)
    }

    private fun renderLoadingState() {
        adapter?.status = AdapterStatus.LOADING
        snackbar?.dismiss()
    }

    private fun renderLoadedState(state: MoviesState) {
        adapter?.status = AdapterStatus.IDLE
        adapter?.setItems(state.movies, reloadItems)
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
                        page = adapter?.nextPage()
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
        val uri = Uri.parse(MOVIE_DETAILS_DEEP_LINK + slash() + movie.id)
        findNavController().navigate(uri)
    }

    /* CALLBACK */

    override fun onCloseClicked() {
        viewModel.process(
            MoviesIntent.ReloadMovies()
        )
    }

    override fun onYearClicked() {
        activity?.supportFragmentManager?.let { fragmentManager ->
            MovieYearPickerFragment { year -> onYearPicked(year) }
                .show(fragmentManager)
        }
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
        activity?.supportFragmentManager?.let { fragmentManager ->
            MovieMonthPickerFragment { month -> onMonthPicked(month) }
                .show(fragmentManager)
        }
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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            binding.toolbar.onRestoreInstanceState(it)
            onRestoreDoReloadInstanceState(it)
        }
    }

    private fun onRestoreDoReloadInstanceState(savedInstanceState: Bundle) {
        reloadItems = savedInstanceState.getBoolean(INSTANCE_STATE_KEY_RELOAD_ITEMS)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        view?.let { binding.toolbar.onSaveInstanceState(outState) }
        onSaveDoReloadInstanceState(outState)
    }

    private fun onSaveDoReloadInstanceState(outState: Bundle) {
        outState.putBoolean(INSTANCE_STATE_KEY_RELOAD_ITEMS, true)
    }

}
