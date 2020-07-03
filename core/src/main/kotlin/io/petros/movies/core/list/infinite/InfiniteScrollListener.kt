package io.petros.movies.core.list.infinite

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class InfiniteScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val listener: Listener,
) : RecyclerView.OnScrollListener() {

    private var pageSize = 0
    private var currentItemsCount = 0
    private var previousItemsCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy > 0) {
            updatePageSize()
            checkAndLoadMore()
        }
    }

    private fun updatePageSize() {
        currentItemsCount = layoutManager.itemCount
        if (currentItemsCount > previousItemsCount) pageSize = max(pageSize, currentItemsCount - previousItemsCount)
        previousItemsCount = currentItemsCount
    }

    private fun checkAndLoadMore() {
        if (!isLoading() and
            hasNextPage() and
            isVisiblePageThresholdBreached(layoutManager.findLastVisibleItemPosition())
        ) {
            listener.loadMore()
        }
    }

    private fun isLoading() = listener.isLoading()

    private fun hasNextPage() = listener.nextPage() != null

    private fun isVisiblePageThresholdBreached(lastVisibleItemPosition: Int): Boolean {
        val visibleThreshold = lastVisibleItemPosition + pageSize
        return visibleThreshold > currentItemsCount
    }

    interface Listener {

        fun nextPage(): Int?

        fun loadMore(): Unit?

        fun isLoading(): Boolean

    }

}
