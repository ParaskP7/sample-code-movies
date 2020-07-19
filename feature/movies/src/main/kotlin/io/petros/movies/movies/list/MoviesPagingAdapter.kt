package io.petros.movies.movies.list

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.list.item.MovieItemView

class MoviesPagingAdapter : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MOVIE_COMPARATOR) {

    companion object {

        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
        }

    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    var context: Context? = null

    var itemCallback: MovieItemCallback? = null

    /* CONTEXT */

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        context = recyclerView.context
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        context = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    /* ITEM VIEW HOLDER */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        context?.let { MovieViewHolder(MovieItemView(it), itemCallback) }
            ?: throw IllegalArgumentException("Context not initialised.")

    @Suppress("UnsafeCast")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as MovieViewHolder).bind(it) }
    }

}
