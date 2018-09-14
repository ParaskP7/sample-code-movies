package io.petros.movies.presentation.feature.common.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class InfiniteScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val listener: Listener
) : RecyclerView.OnScrollListener() {

    private var pageSize = 0
    private var currentItemsCount = 0
    private var previousItemsCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        updatePageSize()
        checkAndLoadMore()
    }

    private fun updatePageSize() {
        currentItemsCount = layoutManager.itemCount
        if (currentItemsCount > previousItemsCount) pageSize = Math.max(pageSize, currentItemsCount - previousItemsCount)
        previousItemsCount = currentItemsCount
    }

    private fun checkAndLoadMore() {
        if (!isLoading() and
            hasNextPage() and
            isVisiblePageThresholdBreached(layoutManager.findLastVisibleItemPosition())) {
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

        fun loadMore()

        fun isLoading(): Boolean

    }

}
