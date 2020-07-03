package io.petros.movies.movies.list

import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.list.item.MovieItemView

class MovieViewHolder(
    itemView: MovieItemView,
    private val itemCallback: MovieItemCallback?,
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
        (itemView as MovieItemView).bindCallback(movie, itemCallback)
    }

}
