package io.petros.movies.movie_details

import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.core.fragment.MviFragment
import io.petros.movies.core.image.glide.displayImage
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.feature.movie.details.R
import io.petros.movies.feature.movie.details.databinding.MovieDetailsFragmentBinding
import io.petros.movies.movie_details.stateful.StatefulMovieDetailsStateListener
import io.petros.movies.movie_details.stateful.stateful
import io.petros.movies.utils.doNothing
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("SyntheticAccessor", "PARAMETER_NAME_CHANGED_ON_OVERRIDE")
class MovieDetailsFragment : MviFragment<
        MovieDetailsFragmentBinding,
        MovieDetailsIntent,
        MovieDetailsState,
        MovieDetailsSideEffect>(R.layout.movie_details_fragment),
    StatefulMovieDetailsStateListener {

    companion object {

        private const val ARGS_MOVIE_ID = "movie_id"

        private fun getMovieId(arguments: Bundle?): Int = arguments?.getInt(ARGS_MOVIE_ID)
            ?: throw UninitializedPropertyAccessException("'movie_id' was queried before being initialized.")

    }

    override val binding by viewBinding(MovieDetailsFragmentBinding::bind)
    override val viewModel: MovieDetailsViewModel by viewModel()
    override val stateful by stateful()
    private val movieId: Int by lazy { getMovieId(arguments) }

    private var snackbar: Snackbar? = null

    override fun onResume() {
        super.onResume()
        viewModel.process(
            MovieDetailsIntent.IdleMovies
        )
    }

    override fun onDestroyView() {
        snackbar?.dismiss()
        snackbar = null
        super.onDestroyView()
    }

    /* STATE */

    override fun onStatusUpdated(status: MovieDetailsStatus) {
        when (status) {
            is MovieDetailsStatus.Init -> viewModel.process(
                MovieDetailsIntent.LoadMovie(
                    id = movieId
                )
            )
            is MovieDetailsStatus.Idle -> doNothing
            is MovieDetailsStatus.Loading -> binding.pbLoading.isVisible = true
            is MovieDetailsStatus.Loaded -> binding.pbLoading.isVisible = false
        }
    }

    override fun onMovieUpdated(movie: Movie) {
        binding.ivBackdrop.displayImage(movie.backdrop)
        binding.tvTitle.text = movie.title
        binding.tvReleaseDate.text = movie.releaseDate()
        binding.tvVote.text = movie.vote()
        binding.tvOverview.text = movie.overview
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
