package io.petros.movies.movie_details

import androidx.lifecycle.viewModelScope
import io.petros.movies.core.view_model.MviViewModel
import io.petros.movies.domain.interactor.movie.LoadMovieUseCase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailsViewModel(
    private val loadMovieUseCase: LoadMovieUseCase,
) : MviViewModel<MovieDetailsIntent, MovieDetailsState, MovieDetailsSideEffect>() {

    init {
        state = MovieDetailsReducer.init()
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun process(intent: MovieDetailsIntent) {
        super.process(intent)
        when (intent) {
            is MovieDetailsIntent.IdleMovies -> idleMovieDetails()
            is MovieDetailsIntent.LoadMovie -> loadMovies(intent.id)
        }
    }

    private fun idleMovieDetails() {
        state = MovieDetailsReducer.reduce(state, MovieDetailsAction.Idle)
    }

    private fun loadMovies(id: Int) = viewModelScope.launch {
        state = MovieDetailsReducer.reduce(state, MovieDetailsAction.Load)
        loadMovieUseCase(LoadMovieUseCase.Params(id))
            .collectLatest { movie ->
                when (movie) {
                    is Result.Success -> onLoadMoviesSuccess(movie.value)
                    is Result.Error -> onLoadMoviesError(movie.cause)
                }
            }
    }

    private fun onLoadMoviesSuccess(movie: Movie) {
        Timber.d("Load movie success. [Movie: $movie]")
        state = MovieDetailsReducer.reduce(state, MovieDetailsAction.Success(movie))
    }

    private fun onLoadMoviesError(error: Exception) {
        Timber.w(error, "Load movie error.")
        state = MovieDetailsReducer.reduce(state, MovieDetailsAction.Error)
        sideEffect = MovieDetailsReducer.once(MovieDetailsAction.Error)
    }

}
