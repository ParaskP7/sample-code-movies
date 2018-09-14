package io.petros.movies.presentation.feature.common.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class InfiniteRecyclerView : RecyclerView, InfiniteScrollListener.Listener {

    var listener: Listener? = null

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        initOnScrollListener()
    }

    private fun initOnScrollListener() {
        val layoutManager = layoutManager?.let { it as LinearLayoutManager } ?: LinearLayoutManager(context)
        val listener = InfiniteScrollListener(layoutManager, this)
        addOnScrollListener(listener)
    }

    /* INFINITE SCROLL LISTENER */

    override fun nextPage(): Int? {
        return (adapter as? InfiniteAdapter<*>)?.nextPage()
    }

    override fun loadMore() {
        listener?.loadData(nextPage())
    }

    override fun isLoading(): Boolean {
        return (adapter as? InfiniteAdapter<*>)?.isLoading() ?: false
    }

    /* INTERFACES */

    interface Listener {

        fun loadDataOrRestore()

        fun loadData(page: Int? = null)

    }

}
