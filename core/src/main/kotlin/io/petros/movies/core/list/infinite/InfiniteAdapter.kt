package io.petros.movies.core.list.infinite

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.domain.model.common.PaginationData

abstract class InfiniteAdapter<T>(
    private val items: ArrayList<T> = arrayListOf(),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    var context: Context? = null

    private var nextPage: Int? = null

    fun items(): List<T> = items

    fun item(position: Int): T = items[position]

    fun nextPage() = nextPage

    /* STATUS */

    abstract fun isLoading(): Boolean

    /* CONTEXT */

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        context = recyclerView.context
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        context = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    /* ITEMS */

    fun setItems(paginationData: PaginationData<T>, reloadItems: Boolean) {
        if (isFirstPage(paginationData) || reloadItems) reloadItems(paginationData) else appendItems(paginationData)
        notifyDataSetChanged()
    }

    private fun isFirstPage(paginationData: PaginationData<T>) =
        paginationData.allPageItems == paginationData.latestPage?.items

    private fun reloadItems(paginationData: PaginationData<T>) {
        items.clear()
        items.addAll(paginationData.allPageItems)
        nextPage = paginationData.nextPage
    }

    private fun appendItems(paginationData: PaginationData<T>) {
        paginationData.latestPage?.items?.let { items.addAll(it) }
        nextPage = paginationData.nextPage
    }

}
