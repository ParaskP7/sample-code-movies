package io.petros.movies.moviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.core.fragment.MviFragment
import io.petros.movies.core.image.glide.displayImage
import io.petros.movies.core.viewbinding.viewBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.feature.movie.details.R
import io.petros.movies.feature.movie.details.databinding.MovieDetailsFragmentBinding
import io.petros.movies.utils.doNothing
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("SyntheticAccessor")
class MovieDetailsFragment : MviFragment<MovieDetailsFragmentBinding,
        MovieDetailsIntent,
        MovieDetailsState,
        MovieDetailsSideEffect>(R.layout.movie_details_fragment) {

    override val binding by viewBinding(MovieDetailsFragmentBinding::bind)
    override val viewModel: MovieDetailsViewModel by viewModel()
    private val movieId: Int by lazy { getMovieId(arguments) }

    private var snackbar: Snackbar? = null

    override fun onResume() {
        super.onResume()
        viewModel.process(
            MovieDetailsIntent.IdleMovies
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroyView() {
        snackbar?.dismiss()
        snackbar = null
        super.onDestroyView()
    }

    /* STATE */

    override fun renderState(state: MovieDetailsState) {
        renderStatus(state.status)
        renderMovie(state.movie)
    }

    private fun renderStatus(status: MovieDetailsStatus) {
        when (status) {
            is MovieDetailsStatus.Init -> viewModel.process(
                MovieDetailsIntent.LoadMovie(
                    id = movieId,
                )
            )
            is MovieDetailsStatus.Idle -> doNothing
            is MovieDetailsStatus.Loading -> binding.pbLoading.isVisible = true
            is MovieDetailsStatus.Loaded -> binding.pbLoading.isVisible = false
        }
    }

    private fun renderMovie(movie: Movie) {
        binding.ivBackdrop.displayImage(movie.backdrop)
        binding.tvTitle.text = movie.title
        binding.tvReleaseDate.text = movie.releaseDate()
        binding.tvVote.text = movie.vote()
        binding.tvOverview.text = movie.overview
    }

    /* SIDE EFFECT */

    @Suppress("OptionalUnit")
    override fun renderSideEffect(sideEffect: MovieDetailsSideEffect): Unit = when (sideEffect) {
        is MovieDetailsSideEffect.Error -> renderErrorSideEffect()
    }

    private fun renderErrorSideEffect() {
        snackbar = Snackbar
            .make(binding.ctrMovieDetails, R.string.sbLoadMovieError, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.sbLoadMovieErrorAction) {
                viewModel.process(
                    MovieDetailsIntent.LoadMovie(
                        id = movieId,
                    )
                )
            }
        snackbar?.show()
    }

    companion object {

        private const val ARGS_MOVIE_ID = "movie_id"

        private fun getMovieId(arguments: Bundle?): Int = arguments?.getInt(ARGS_MOVIE_ID)
            ?: throw UninitializedPropertyAccessException("'movie_id' was queried before being initialized.")

    }

}
