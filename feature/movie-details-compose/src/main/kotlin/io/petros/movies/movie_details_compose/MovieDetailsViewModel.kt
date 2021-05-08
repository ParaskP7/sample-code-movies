package io.petros.movies.movie_details_compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.petros.movies.domain.interactor.movie.LoadMovieUseCase
import io.petros.movies.domain.model.Result
import io.petros.movies.domain.model.movie.Movie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailsViewModel(
    private val loadMovieUseCase: LoadMovieUseCase,
) : ViewModel() {

    var state: MovieDetailsState by mutableStateOf(MovieDetailsReducer.init())
        private set
    var sideEffect: MovieDetailsSideEffect by mutableStateOf(MovieDetailsSideEffect.Init)
        private set

    fun process(intent: MovieDetailsIntent) {
        when (intent) {
            is MovieDetailsIntent.LoadMovie -> loadMovies(intent.id)
        }
    }

    private fun loadMovies(id: Int) = viewModelScope.launch {
        state = MovieDetailsReducer.reduce(state, MovieDetailsAction.Load)
        sideEffect = MovieDetailsReducer.once(MovieDetailsAction.Load)
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
