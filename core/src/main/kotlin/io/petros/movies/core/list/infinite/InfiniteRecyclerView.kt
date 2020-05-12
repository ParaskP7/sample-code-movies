package io.petros.movies.core.list.infinite

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteRecyclerView(
    ctx: Context,
    attrs: AttributeSet? = null
) : RecyclerView(ctx, attrs),
    InfiniteScrollListener.Listener {

    var listener: Listener? = null

    init {
        initOnScrollListener()
    }

    @Suppress("UnsafeCast")
    private fun initOnScrollListener() {
        val layoutManager = layoutManager?.let { it as LinearLayoutManager } ?: LinearLayoutManager(context)
        val listener = InfiniteScrollListener(layoutManager, this)
        addOnScrollListener(listener)
    }

    /* INFINITE SCROLL LISTENER */

    override fun nextPage() = (adapter as? InfiniteAdapter<*>)?.nextPage()

    override fun loadMore() = listener?.loadData(nextPage())

    override fun isLoading() = (adapter as? InfiniteAdapter<*>)?.isLoading() ?: false

    /* INTERFACES */

    interface Listener {

        fun loadData(page: Int? = null)

    }

}
