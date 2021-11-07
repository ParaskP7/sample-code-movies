@file:Suppress("MagicNumber")

package io.petros.movies.movie_details_compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import io.petros.movies.core_compose.ui.Theme
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("SyntheticAccessor")
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private val movieId: Int by lazy { getMovieId(arguments) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Theme {
                MovieDetailsScreen(
                    state = viewModel.state,
                    sideEffect = viewModel.sideEffect,
                    onRetry = {
                        viewModel.process(
                            MovieDetailsIntent.LoadMovie(
                                id = movieId,
                            )
                        )
                    }
                )
            }
        }
        start()
    }

    private fun start() {
        if (viewModel.state.status == MovieDetailsStatus.Init) {
            viewModel.process(
                MovieDetailsIntent.LoadMovie(
                    id = movieId,
                )
            )
        }
    }

    companion object {

        private const val ARGS_MOVIE_ID = "movie_id"

        private fun getMovieId(arguments: Bundle?): Int = arguments?.getInt(ARGS_MOVIE_ID)
            ?: throw UninitializedPropertyAccessException("'movie_id' was queried before being initialized.")

    }

}
