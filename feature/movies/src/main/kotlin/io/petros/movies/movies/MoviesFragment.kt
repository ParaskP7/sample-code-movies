package io.petros.movies.movies

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.core.fragment.MviFragment
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.core.list.infinite.InfiniteRecyclerView
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.feature.movies.R
import io.petros.movies.feature.movies.databinding.MoviesFragmentBinding
import io.petros.movies.movies.list.MoviesAdapter
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.stateful.StatefulMoviesStateListener
import io.petros.movies.movies.toolbar.MoviesToolbarCallback
import io.petros.movies.picker.MovieMonthPickerFragment
import io.petros.movies.picker.MovieYearPickerFragment
import io.petros.movies.utils.doNothing
import io.petros.movies.utils.slash
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("TooManyFunctions", "PARAMETER_NAME_CHANGED_ON_OVERRIDE")
class MoviesFragment : MviFragment<
        MoviesIntent,
        MoviesState,
        MoviesSideEffect>(R.layout.movies_fragment),
    StatefulMoviesStateListener,
    MoviesToolbarCallback,
    MovieItemCallback,
    InfiniteRecyclerView.Listener {

    companion object {

        private const val INSTANCE_STATE_KEY_RELOAD_ITEMS = "RELOAD_ITEMS"

        private const val MOVIE_DETAILS_DEEP_LINK = "io.petros.movies://movieDetailsFragment"

    }

    private val binding by viewBinding(MoviesFragmentBinding::bind)
    override val viewModel: MoviesViewModel by viewModel()
    override val stateful by stateful()

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
        viewModel.process(
            MoviesIntent.IdleMovies(
                year = viewModel.state().value?.year,
                month = viewModel.state().value?.month
            )
        )
    }

    override fun onDestroyView() {
        adapter = null
        snackbar?.dismiss()
        snackbar = null
        super.onDestroyView()
    }

    /* STATE */

    override fun onStatusUpdated(status: MoviesStatus) {
        when (status) {
            is MoviesStatus.Init -> viewModel.process(
                MoviesIntent.LoadMovies()
            )
            is MoviesStatus.Idle -> adapter?.status = AdapterStatus.IDLE
            is MoviesStatus.Loading -> {
                adapter?.status = AdapterStatus.LOADING
                snackbar?.dismiss()
            }
            is MoviesStatus.Loaded -> adapter?.status = AdapterStatus.IDLE
        }
    }

    override fun onYearUpdated(year: Int?) {
        if (year != null) {
            binding.toolbar.showCloseIcon()
            binding.toolbar.setYear(year)
        } else {
            binding.toolbar.showFilterIcon()
            binding.toolbar.hideYear()
            binding.toolbar.hideMonth()
        }
    }

    override fun onMonthUpdated(month: Int?) {
        if (month != null) {
            binding.toolbar.setMonth(month)
        } else {
            binding.toolbar.hideMonth()
        }
    }

    @Suppress("UseCheckOrError")
    override fun onMoviesUpdated(state: MoviesState) {
        when (state.status) {
            is MoviesStatus.Init -> doNothing
            is MoviesStatus.Idle -> adapter?.setItems(state.movies, true)
            is MoviesStatus.Loading -> throw IllegalStateException("Movies updated during loading status.")
            is MoviesStatus.Loaded -> {
                adapter?.setItems(state.movies, reloadItems)
                reloadItems = false
            }
        }
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
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
        findNavController().navigate(uri, options)
    }

    /* CALLBACK */

    override fun onFilterClicked() {
        binding.toolbar.showCloseIcon()
        binding.toolbar.showYear()
    }

    override fun onCloseClicked() {
        if (binding.toolbar.getYear() != null) {
            viewModel.process(
                MoviesIntent.ReloadMovies()
            )
        } else {
            binding.toolbar.showFilterIcon()
            binding.toolbar.hideYear()
        }
    }

    override fun onYearClicked() {
        activity?.supportFragmentManager?.let { fragmentManager ->
            MovieYearPickerFragment { year -> onYearPicked(year) }
                .show(fragmentManager)
        }
    }

    private fun onYearPicked(year: Int) {
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
        savedInstanceState?.let { bundle ->
            binding.toolbar.onRestoreInstanceState(bundle)
            onRestoreDoReloadInstanceState(bundle)
        }
    }

    private fun onRestoreDoReloadInstanceState(savedInstanceState: Bundle) {
        reloadItems = savedInstanceState.getBoolean(INSTANCE_STATE_KEY_RELOAD_ITEMS)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (view != null) binding.toolbar.onSaveInstanceState(outState)
        onSaveDoReloadInstanceState(outState)
    }

    private fun onSaveDoReloadInstanceState(outState: Bundle) {
        outState.putBoolean(INSTANCE_STATE_KEY_RELOAD_ITEMS, true)
    }

}
