package io.petros.movies.movies.list

import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movies.list.item.MovieItemView
import io.petros.movies.movies.listener.MovieCallback

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
