package io.petros.movies.presentation.feature.common.toolbar

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import android.view.View
import io.petros.movies.R
import io.petros.movies.presentation.inflate
import kotlinx.android.synthetic.main.toolbar_movies.view.*

class MoviesToolbar : AppBarLayout {

    companion object {

        private const val INSTANCE_STATE_KEY_YEAR_FILTER = "YEAR_FILTER"

    }

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    lateinit var callback: MoviesToolbarCallback

    init {
        inflate(R.layout.toolbar_movies)
        initFilterIcon()
        initYearFilter()
    }

    private fun initFilterIcon() {
        iv_filter_icon.setOnClickListener { showYear() }
    }

    private fun showYear() {
        tv_filter_year.visibility = View.VISIBLE
    }

    private fun initYearFilter() {
        tv_filter_year.setOnClickListener { callback.onYearClicked() }
    }

    fun setYear(year: Int) {
        tv_filter_year.text = year.toString()
    }

    fun getYear(): Int? {
        return try {
            tv_filter_year.text.toString().toInt()
        } catch (nfe: NumberFormatException) {
            null
        }
    }

    /* CONFIGURATION CHANGE */

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val year = savedInstanceState.getInt(INSTANCE_STATE_KEY_YEAR_FILTER)
        if (year != 0) {
            showYear()
            setYear(year)
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        getYear()?.let { outState.putInt(INSTANCE_STATE_KEY_YEAR_FILTER, it) }
    }

}
