package io.petros.movies.movies.list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.core.list.AdapterStatus
import io.petros.movies.core.list.ProgressViewHolder
import io.petros.movies.core.list.infinite.InfiniteAdapter
import io.petros.movies.core.list.item.ProgressItemView
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movies.list.item.MovieItemCallback
import io.petros.movies.movies.list.item.MovieItemView
import kotlin.math.max

class MoviesAdapter(
    items: ArrayList<Movie> = arrayListOf(),
) : InfiniteAdapter<Movie>(items) {

    companion object {
        internal const val VIEW_TYPE_PROGRESS = 0
        internal const val VIEW_TYPE_MOVIE = 1
    }

    var itemCallback: MovieItemCallback? = null

    var status = AdapterStatus.IDLE
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /* STATUS */

    override fun isLoading() = status == AdapterStatus.LOADING

    /* ITEMS */

    override fun getItemCount(): Int {
        val statusCount = if (status != AdapterStatus.IDLE) 1 else 0
        return items().size + statusCount
    }

    /* ITEM VIEW HOLDER */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        context?.let { onCreateViewHolderWithContext(viewType, it) }
            ?: throw IllegalArgumentException("Context not initialised.")

    private fun onCreateViewHolderWithContext(viewType: Int, context: Context) = when (viewType) {
        VIEW_TYPE_MOVIE -> MovieViewHolder(MovieItemView(context), itemCallback)
        VIEW_TYPE_PROGRESS -> ProgressViewHolder(ProgressItemView(context))
        else -> throw IllegalArgumentException("View type out of range. [View Type: $viewType]")
    }

    @Suppress("UnsafeCast")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_MOVIE) {
            (holder as MovieViewHolder).bind(item(position))
        }
    }

    /* NAVIGATION */

    override fun getItemViewType(position: Int) =
        if (isAtLastPosition(position)) getItemViewTypeAtLastPosition() else VIEW_TYPE_MOVIE

    private fun getItemViewTypeAtLastPosition() = when (status) {
        AdapterStatus.IDLE -> VIEW_TYPE_MOVIE
        AdapterStatus.LOADING -> VIEW_TYPE_PROGRESS
    }

    private fun isAtLastPosition(position: Int) = position == max(itemCount - 1, 0)

}
