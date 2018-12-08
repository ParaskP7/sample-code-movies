package io.petros.movies.presentation.feature.movies.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.R
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.common.list.adapter.AdapterStatus
import io.petros.movies.presentation.feature.common.list.holder.ErrorViewHolder
import io.petros.movies.presentation.feature.common.list.holder.ProgressViewHolder
import io.petros.movies.presentation.feature.common.list.item.ErrorItemView
import io.petros.movies.presentation.feature.common.list.item.ProgressItemView
import io.petros.movies.presentation.feature.common.view.InfiniteAdapter
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.view.MovieItemView
import io.petros.movies.presentation.toast

class MoviesAdapter : InfiniteAdapter<Movie>() {

    companion object {
        internal const val VIEW_TYPE_PROGRESS = 0
        internal const val VIEW_TYPE_MOVIE = 1
        internal const val VIEW_TYPE_ERROR = 101
    }

    @Suppress("LateinitUsage")
    lateinit var callback: MovieCallback

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
        return items.size + statusCount
    }

    /* ITEM VIEW HOLDER */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context?.let { context ->
            return when (viewType) {
                VIEW_TYPE_PROGRESS -> ProgressViewHolder(ProgressItemView(context))
                VIEW_TYPE_MOVIE -> MovieViewHolder(MovieItemView(context), callback)
                VIEW_TYPE_ERROR -> ErrorViewHolder(ErrorItemView(context)) { context.toast(R.string.retry_loading) }
                else -> throw IllegalArgumentException("View type out of range. [View Type: $viewType]")
            }
        }
        throw IllegalArgumentException("Context not initialised.")
    }

    @Suppress("UnsafeCast")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_MOVIE) {
            (holder as MovieViewHolder).bind(items[position])
        }
    }

    /* NAVIGATION */

    override fun getItemViewType(position: Int): Int {
        return when {
            isAtLastPosition(position) -> when (status) {
                AdapterStatus.IDLE -> VIEW_TYPE_MOVIE
                AdapterStatus.LOADING -> VIEW_TYPE_PROGRESS
                AdapterStatus.ERROR -> VIEW_TYPE_ERROR
            }
            else -> VIEW_TYPE_MOVIE
        }
    }

    private fun isAtLastPosition(position: Int) = position == Math.max(itemCount - 1, 0)

}
