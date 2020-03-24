package io.petros.movies.presentation.feature.common.toolbar

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.appbar.AppBarLayout
import io.petros.movies.R
import io.petros.movies.android_utils.inflate
import io.petros.movies.utils.MonthOfYear
import kotlinx.android.synthetic.main.toolbar_movies.view.*
import timber.log.Timber

@Suppress("TooManyFunctions")
class MoviesToolbar : AppBarLayout { // MRT

    companion object {

        private const val INSTANCE_STATE_KEY_CLOSE_ICON = "CLOSE_ICON"
        private const val INSTANCE_STATE_KEY_YEAR_FILTER = "YEAR_FILTER"
        private const val INSTANCE_STATE_KEY_MONTH_FILTER = "MONTH_FILTER"

    }

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    @Suppress("LateinitUsage")
    lateinit var callback: MoviesToolbarCallback

    init {
        inflate(R.layout.toolbar_movies)
        initFilterIcon()
        initCloseIcon()
        initYearFilter()
        initMonthFilter()
    }

    private fun initFilterIcon() {
        iv_filter_icon.setOnClickListener { onFilterIconClicked() }
    }

    private fun onFilterIconClicked() {
        iv_filter_icon.isVisible = false
        iv_close_icon.isVisible = true
        showYear()
    }

    private fun initCloseIcon() {
        iv_close_icon.setOnClickListener { onCloseIconClicked() }
    }

    private fun onCloseIconClicked() {
        iv_close_icon.isVisible = false
        iv_filter_icon.isVisible = true
        hideYear()
        hideMonth()
        callback.onCloseClicked()
    }

    private fun initYearFilter() {
        tv_filter_year.setOnClickListener { callback.onYearClicked() }
    }

    private fun initMonthFilter() {
        tv_filter_month.setOnClickListener { callback.onMonthClicked() }
    }

    /* SHOW/HIDE */

    @Suppress("MemberVisibilityCanBePrivate")
    fun showYear() {
        tv_filter_year.isInvisible = false
        tv_filter_year.text = context.getString(R.string.toolbar_movies_filter_year)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun hideYear() {
        tv_filter_year.isInvisible = true
        tv_filter_year.text = context.getString(R.string.toolbar_movies_filter_year)
    }

    fun showMonth() {
        tv_filter_month.isInvisible = false
        tv_filter_month.text = context.getString(R.string.toolbar_movies_filter_month)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun hideMonth() {
        tv_filter_month.isInvisible = true
        tv_filter_month.text = context.getString(R.string.toolbar_movies_filter_month)
    }

    /* YEAR */

    fun setYear(year: Int) {
        tv_filter_year.text = year.toString()
    }

    @Suppress("SwallowedException")
    fun getYear(): Int? {
        val year = tv_filter_year.text.toString()
        return try {
            year.toInt()
        } catch (nfe: NumberFormatException) {
            Timber.d("Cannot convert the year text to an integer, defaulting to null. [Year: $year]")
            null
        }
    }

    /* MONTH */

    fun setMonth(month: Int) {
        tv_filter_month.text = MonthOfYear.from(month).monthName
    }

    fun getMonth() = MonthOfYear.from(tv_filter_month.text).month

    /* CONFIGURATION CHANGE */

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        onRestoreIconInstanceState(savedInstanceState)
        onRestoreYearInstanceState(savedInstanceState)
        onRestoreMonthInstanceState(savedInstanceState)
    }

    private fun onRestoreIconInstanceState(savedInstanceState: Bundle) {
        val closeIcon = savedInstanceState.getBoolean(INSTANCE_STATE_KEY_CLOSE_ICON)
        if (closeIcon) onFilterIconClicked()
    }

    private fun onRestoreYearInstanceState(savedInstanceState: Bundle) {
        val year = savedInstanceState.getString(INSTANCE_STATE_KEY_YEAR_FILTER)
        if (year != null) {
            showYear()
            showMonth()
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
        onSaveCloseIconInstanceState(outState)
        onSaveYearInstanceState(outState)
        onSaveMonthInstanceState(outState)
    }

    private fun onSaveCloseIconInstanceState(outState: Bundle) {
        outState.putBoolean(INSTANCE_STATE_KEY_CLOSE_ICON, iv_close_icon.isVisible)
    }

    private fun onSaveYearInstanceState(outState: Bundle) {
        getYear()?.let { outState.putString(INSTANCE_STATE_KEY_YEAR_FILTER, it.toString()) }
    }

    private fun onSaveMonthInstanceState(outState: Bundle) {
        getMonth()?.let { outState.putString(INSTANCE_STATE_KEY_MONTH_FILTER, it.toString()) }
    }

}
