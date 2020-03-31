package io.petros.movies.core.list.infinite

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import io.petros.movies.domain.model.common.PaginationData

abstract class InfiniteAdapter<T>(
    private val items: ArrayList<T> = arrayListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    var context: Context? = null

    private var paginationData: PaginationData<T>? = null

    fun items(): List<T> = items

    fun item(position: Int): T = items[position]

    fun nextPage() = paginationData?.nextPage()

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

    fun setItems(paginationData: PaginationData<T>) {
        val shouldReload = shouldReload(paginationData)
        this.paginationData = paginationData
        if (shouldReload) reloadItems(paginationData) else appendItems(paginationData)
        notifyDataSetChanged()
    }

    private fun shouldReload(paginationData: PaginationData<T>): Boolean {
        return paginationData.isFirstPage() ||
                paginationData == this.paginationData
    }

    private fun reloadItems(paginationData: PaginationData<T>) {
        items.clear()
        items.addAll(paginationData.items())
    }

    private fun appendItems(paginationData: PaginationData<T>) {
        paginationData.latestItems()?.let { items.addAll(it) }
    }

}
