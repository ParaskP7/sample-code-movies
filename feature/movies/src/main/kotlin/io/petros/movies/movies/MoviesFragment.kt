package io.petros.movies.movies

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadType
import androidx.paging.PagingData
import com.google.android.material.snackbar.Snackbar
import dev.fanie.stateful.Renders
import io.petros.movies.core.fragment.MviFragment
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.feature.movies.R
import io.petros.movies.feature.movies.databinding.MoviesFragmentBinding
import io.petros.movies.movies.list.MoviesPagingAdapter
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.stateful.moviesstate.StatefulMoviesState
import io.petros.movies.movies.stateful.moviesstate.stateful
import io.petros.movies.movies.toolbar.MoviesToolbarCallback
import io.petros.movies.picker.MovieMonthPickerFragment
import io.petros.movies.picker.MovieYearPickerFragment
import io.petros.movies.utils.doNothing
import io.petros.movies.utils.slash
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@Suppress("TooManyFunctions", "unused")
class MoviesFragment : MviFragment<
        MoviesFragmentBinding,
        MoviesIntent,
        MoviesState,
        MoviesSideEffect,
        >(R.layout.movies_fragment),
    MoviesToolbarCallback,
    MovieItemCallback {

    companion object {

        private const val MOVIE_DETAILS_DEEP_LINK = "io.petros.movies://movieDetailsFragment"

    }

    override val binding by viewBinding(MoviesFragmentBinding::bind)
    override val viewModel: MoviesViewModel by viewModel()
    override val stateful by stateful()

    private var adapter: MoviesPagingAdapter? = null
    private var snackbar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        binding.toolbar.callback = this
    }

    private fun initRecyclerView() {
        adapter = MoviesPagingAdapter()
        adapter?.itemCallback = this
        adapter?.addLoadStateListener { handleErrorLoadStates(it) }
        binding.recyclerView.adapter = adapter
    }

    private fun handleErrorLoadStates(loadState: CombinedLoadStates) {
        refreshErrorState(loadState)?.let {
            viewModel.process(
                MoviesIntent.ErrorMovies(
                    error = it.error,
                    loadType = LoadType.REFRESH,
                )
            )
        }
        appendErrorState(loadState)?.let {
            viewModel.process(
                MoviesIntent.ErrorMovies(
                    error = it.error,
                    loadType = LoadType.APPEND,
                )
            )
        }
        prependErrorState(loadState)?.let {
            viewModel.process(
                MoviesIntent.ErrorMovies(
                    error = it.error,
                    loadType = LoadType.PREPEND,
                )
            )
        }
    }

    private fun refreshErrorState(loadState: CombinedLoadStates) =
        loadState.source.refresh as? LoadState.Error
            ?: loadState.refresh as? LoadState.Error

    private fun appendErrorState(loadState: CombinedLoadStates) =
        loadState.source.append as? LoadState.Error
            ?: loadState.append as? LoadState.Error

    private fun prependErrorState(loadState: CombinedLoadStates) =
        loadState.source.prepend as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error

    override fun onResume() {
        super.onResume()
        viewModel.process(
            MoviesIntent.IdleMovies(
                year = viewModel.state().value?.year,
                month = viewModel.state().value?.month,
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

    @Renders(StatefulMoviesState.Property.STATUS::class)
    fun renderStatus(status: MoviesStatus) {
        when (status) {
            is MoviesStatus.Init -> viewModel.process(
                MoviesIntent.LoadMovies()
            )
            is MoviesStatus.Idle -> doNothing
            is MoviesStatus.Loading -> snackbar?.dismiss()
            is MoviesStatus.Loaded -> doNothing
        }
    }

    @Renders(StatefulMoviesState.Property.YEAR::class)
    fun renderYear(year: Int?) {
        if (year != null) {
            binding.toolbar.showCloseIcon()
            binding.toolbar.setYear(year)
        } else {
            binding.toolbar.showFilterIcon()
            binding.toolbar.hideYear()
            binding.toolbar.hideMonth()
        }
    }

    @Renders(StatefulMoviesState.Property.MONTH::class)
    fun renderMonth(month: Int?) {
        if (month != null) {
            binding.toolbar.setMonth(month)
        } else {
            binding.toolbar.hideMonth()
        }
    }

    @Renders(StatefulMoviesState.Property.MOVIES::class)
    fun renderMovies(movies: PagingData<Movie>) {
        lifecycleScope.launch { adapter?.submitData(movies) }
    }

    /* SIDE EFFECT */

    override fun renderSideEffect(sideEffect: MoviesSideEffect) = when (sideEffect) {
        is MoviesSideEffect.MoviesRefreshError -> Timber.d("Paging error during movies refresh.")
        is MoviesSideEffect.MoviesAppendError -> renderErrorSideEffect()
        is MoviesSideEffect.MoviesPrependError -> renderErrorSideEffect()
    }

    private fun renderErrorSideEffect() {
        snackbar = Snackbar
            .make(binding.ctrMovies, R.string.sbLoadMoviesError, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.sbLoadMoviesErrorAction) {
                viewModel.process(
                    MoviesIntent.LoadMovies(
                        year = binding.toolbar.getYear(),
                        month = binding.toolbar.getMonth(),
                    )
                )
            }
        snackbar?.show()
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
                year = year,
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
                month = month,
            )
        )
    }

    /* CONFIGURATION CHANGE */

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { binding.toolbar.onRestoreInstanceState(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (view != null) binding.toolbar.onSaveInstanceState(outState)
    }

}
