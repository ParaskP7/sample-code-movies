package io.petros.movies.presentation.feature.common.toolbar

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import android.view.View
import io.petros.movies.R
import io.petros.movies.domain.util.MonthOfYear
import io.petros.movies.presentation.inflate
import kotlinx.android.synthetic.main.toolbar_movies.view.*

@Suppress("TooManyFunctions")
class MoviesToolbar : AppBarLayout {

    companion object {

        private const val INSTANCE_STATE_KEY_YEAR_FILTER = "YEAR_FILTER"
        private const val INSTANCE_STATE_KEY_MONTH_FILTER = "MONTH_FILTER"

    }

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    lateinit var callback: MoviesToolbarCallback

    init {
        inflate(R.layout.toolbar_movies)
        initFilterIcon()
        initYearFilter()
        initMonthFilter()
    }

    private fun initFilterIcon() {
        iv_filter_icon.setOnClickListener { showYear() }
    }

    private fun initYearFilter() {
        tv_filter_year.setOnClickListener { callback.onYearClicked() }
    }

    private fun initMonthFilter() {
        tv_filter_month.setOnClickListener { callback.onMonthClicked() }
    }

    /* SHOW */

    fun showYear() {
        tv_filter_year.visibility = View.VISIBLE
        tv_filter_year.text = context.getString(R.string.toolbar_movies_filter_year)
    }

    fun showMonth() {
        tv_filter_month.visibility = View.VISIBLE
        tv_filter_month.text = context.getString(R.string.toolbar_movies_filter_month)
    }

    /* YEAR */

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

    /* MONTH */

    fun setMonth(month: Int) {
        tv_filter_month.text = MonthOfYear.from(month).monthName
    }

    fun getMonth(): Int? {
        return MonthOfYear.from(tv_filter_month.text).month
    }

    /* CONFIGURATION CHANGE */

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        onRestoreYearInstanceState(savedInstanceState)
        onRestoreMonthInstanceState(savedInstanceState)
    }

    private fun onRestoreYearInstanceState(savedInstanceState: Bundle) {
        val year = savedInstanceState.getString(INSTANCE_STATE_KEY_YEAR_FILTER)
        if (year != null) {
            showYear()
            setYear(year.toInt())
        }
    }

    private fun onRestoreMonthInstanceState(savedInstanceState: Bundle) {
        val month = savedInstanceState.getString(INSTANCE_STATE_KEY_MONTH_FILTER)
        if (month != null) {
            showMonth()
            setMonth(month.toInt())
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        onSaveYearInstanceState(outState)
        onSaveMonthInstanceState(outState)
    }

    private fun onSaveYearInstanceState(outState: Bundle) {
        getYear()?.let { outState.putString(INSTANCE_STATE_KEY_YEAR_FILTER, it.toString()) }
    }

    private fun onSaveMonthInstanceState(outState: Bundle) {
        getMonth()?.let { outState.putString(INSTANCE_STATE_KEY_MONTH_FILTER, it.toString()) }
    }

}
