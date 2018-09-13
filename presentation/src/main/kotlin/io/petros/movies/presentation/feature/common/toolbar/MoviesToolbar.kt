package io.petros.movies.presentation.feature.common.toolbar

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import android.view.View
import io.petros.movies.R
import io.petros.movies.presentation.inflate
import kotlinx.android.synthetic.main.toolbar_movies.view.*

class MoviesToolbar : AppBarLayout {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    lateinit var callback: MoviesToolbarCallback

    init {
        inflate(R.layout.toolbar_movies)
        initFilterIcon()
        initYearFilter()
    }

    private fun initFilterIcon() {
        iv_filter_icon.setOnClickListener { tv_filter_year.visibility = View.VISIBLE }
    }

    private fun initYearFilter() {
        tv_filter_year.setOnClickListener { callback.onYearClicked() }
    }

    fun setYear(year: Int) {
        tv_filter_year.text = year.toString()
    }

}
