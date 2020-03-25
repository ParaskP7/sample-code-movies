package io.petros.movies.presentation.feature.movies.list

import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.view.MovieItemView

class MovieViewHolder(
    itemView: MovieItemView,
    private val callback: MovieCallback?
) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {
        bindMovie(movie)
        bindMovieCallback(movie)
    }

    @Suppress("UnsafeCast")
    private fun bindMovie(movie: Movie) {
        (itemView as MovieItemView).bind(movie)
    }

    @Suppress("UnsafeCast")
    private fun bindMovieCallback(movie: Movie) {
        (itemView as MovieItemView).bindCallback(movie, callback)
    }

}
