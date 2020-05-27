package io.petros.movies.movie_details

import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.core.fragment.MviFragment
import io.petros.movies.core.image.glide.displayImage
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.feature.movie.details.R
import io.petros.movies.feature.movie.details.databinding.MovieDetailsFragmentBinding
import io.petros.movies.utils.doNothing
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("SyntheticAccessor")
class MovieDetailsFragment : MviFragment<
        MovieDetailsIntent,
        MovieDetailsState,
        MovieDetailsSideEffect,
        MovieDetailsViewModel>(R.layout.movie_details_fragment) {

    companion object {

        private const val ARGS_ID = "movie_id"

        private fun getMovieId(arguments: Bundle?): Int = arguments?.getInt(ARGS_ID)
            ?: throw UninitializedPropertyAccessException("'id' was queried before being initialized.")

    }

    private val binding by viewBinding(MovieDetailsFragmentBinding::bind)
    override val viewModel: MovieDetailsViewModel by viewModel()
    private val movieId: Int by lazy { getMovieId(arguments) }

    private var snackbar: Snackbar? = null

    override fun onDestroyView() {
        snackbar?.dismiss()
        snackbar = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.process(
            MovieDetailsIntent.IdleMovies
        )
    }

    /* STATE */

    override fun renderState(state: MovieDetailsState) = when (state.status) {
        is MovieDetailsStatus.Init -> renderInitState()
        is MovieDetailsStatus.Idle -> doNothing
        is MovieDetailsStatus.Loading -> renderLoadingState()
        is MovieDetailsStatus.Loaded -> renderLoadedState(state)
    }

    private fun renderInitState() {
        viewModel.process(
            MovieDetailsIntent.LoadMovie(
                id = movieId
            )
        )
    }

    private fun renderLoadingState() {
        binding.pbLoading.isVisible = true
    }

    private fun renderLoadedState(state: MovieDetailsState) {
        binding.pbLoading.isVisible = false
        binding.ivBackdrop.displayImage(state.movie.backdrop)
        binding.tvTitle.text = state.movie.title
        binding.tvReleaseDate.text = state.movie.releaseDate()
        binding.tvVote.text = state.movie.vote()
        binding.tvOverview.text = state.movie.overview
    }

    /* SIDE EFFECT */

    override fun renderSideEffect(sideEffect: MovieDetailsSideEffect) = when (sideEffect) {
        is MovieDetailsSideEffect.Error -> renderErrorSideEffect()
    }

    private fun renderErrorSideEffect() {
        snackbar = Snackbar
            .make(binding.ctrMovieDetails, R.string.sbLoadMovieError, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.sbLoadMovieErrorAction) {
                viewModel.process(
                    MovieDetailsIntent.LoadMovie(
                        id = movieId
                    )
                )
            }
        snackbar?.show()
    }

}
