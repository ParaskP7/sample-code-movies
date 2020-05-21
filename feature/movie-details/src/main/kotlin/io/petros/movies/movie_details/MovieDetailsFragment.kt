package io.petros.movies.movie_details

import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.core.fragment.MviFragment
import io.petros.movies.core.image.glide.displayImage
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.movie_details.databinding.MovieDetailsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("ForbiddenComment")
class MovieDetailsFragment : MviFragment<
        MovieDetailsIntent,
        MovieDetailsState,
        MovieDetailsSideEffect,
        MovieDetailsViewModel>(R.layout.movie_details_fragment) {

    private val binding by viewBinding(MovieDetailsFragmentBinding::bind)
    private val safeArgs: MovieDetailsFragmentArgs by navArgs()
    override val viewModel: MovieDetailsViewModel by viewModel()

    private var snackbar: Snackbar? = null

    override fun onDestroyView() {
        snackbar = null
        super.onDestroyView()
    }

    /* STATE */

    override fun renderState(state: MovieDetailsState) = when (state.status) {
        is MovieDetailsStatus.Init -> renderInitState()
        is MovieDetailsStatus.Loading -> renderLoadingState()
        is MovieDetailsStatus.Loaded -> renderLoadedState(state)
    }

    private fun renderInitState() {
        safeArgs.movie.id.let {
            viewModel.process(
                MovieDetailsIntent.LoadMovie(
                    id = it
                )
            )
        }
    }

    private fun renderLoadingState() {
        binding.pbLoading.isVisible = true
        binding.ctrLoaded.isVisible = false
    }

    private fun renderLoadedState(state: MovieDetailsState) {
        binding.pbLoading.isVisible = false
        binding.ctrLoaded.isVisible = true
        state.movie?.let {
            binding.ivBackdrop.displayImage(state.movie.backdrop)
            binding.tvTitle.text = state.movie.title
            binding.tvReleaseDate.text = state.movie.releaseDate()
            binding.tvVote.text = state.movie.vote()
            binding.tvOverview.text = state.movie.overview
        }
    }

    /* SIDE EFFECT */

    override fun renderSideEffect(sideEffect: MovieDetailsSideEffect) = when (sideEffect) {
        is MovieDetailsSideEffect.Error -> renderErrorSideEffect()
    }

    private fun renderErrorSideEffect() {
        snackbar = Snackbar
            .make(binding.ctrMovieDetails, R.string.sbLoadMovieError, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.sbLoadMovieErrorAction) {
                safeArgs.movie.id.let {
                    viewModel.process(
                        MovieDetailsIntent.LoadMovie(
                            id = it
                        )
                    )
                }
            }
        snackbar?.show()
    }

}
