package io.petros.movies.presentation.feature.common.toolbar

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.appbar.AppBarLayout
import io.petros.movies.R
import io.petros.movies.databinding.MoviesToolbarBinding
import io.petros.movies.utils.MonthOfYear
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

    var callback: MoviesToolbarCallback? = null

    private val binding = MoviesToolbarBinding.inflate(LayoutInflater.from(context), this)

    init {
        initFilterIcon()
        initCloseIcon()
        initYearFilter()
        initMonthFilter()
    }

    private fun initFilterIcon() {
        binding.ivFilterIcon.setOnClickListener { onFilterIconClicked() }
    }

    private fun onFilterIconClicked() {
        binding.ivFilterIcon.isVisible = false
        binding.ivCloseIcon.isVisible = true
        showYear()
    }

    private fun initCloseIcon() {
        binding.ivCloseIcon.setOnClickListener { onCloseIconClicked() }
    }

    private fun onCloseIconClicked() {
        binding.ivCloseIcon.isVisible = false
        binding.ivFilterIcon.isVisible = true
        hideYear()
        hideMonth()
        callback?.onCloseClicked()
    }

    private fun initYearFilter() {
        binding.tvFilterYear.setOnClickListener { callback?.onYearClicked() }
    }

    private fun initMonthFilter() {
        binding.tvFilterMonth.setOnClickListener { callback?.onMonthClicked() }
    }

    /* SHOW/HIDE */

    @Suppress("MemberVisibilityCanBePrivate")
    fun showYear() {
        binding.tvFilterYear.isInvisible = false
        binding.tvFilterYear.text = context.getString(R.string.toolbar_movies_filter_year)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun hideYear() {
        binding.tvFilterYear.isInvisible = true
        binding.tvFilterYear.text = context.getString(R.string.toolbar_movies_filter_year)
    }

    fun showMonth() {
        binding.tvFilterMonth.isInvisible = false
        binding.tvFilterMonth.text = context.getString(R.string.toolbar_movies_filter_month)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun hideMonth() {
        binding.tvFilterMonth.isInvisible = true
        binding.tvFilterMonth.text = context.getString(R.string.toolbar_movies_filter_month)
    }

    /* YEAR */

    fun setYear(year: Int) {
        binding.tvFilterYear.text = year.toString()
    }

    @Suppress("SwallowedException")
    fun getYear(): Int? {
        val year = binding.tvFilterYear.text.toString()
        return try {
            year.toInt()
        } catch (nfe: NumberFormatException) {
            Timber.d("Cannot convert the year text to an integer, defaulting to null. [Year: $year]")
            null
        }
    }

    /* MONTH */

    fun setMonth(month: Int) {
        binding.tvFilterMonth.text = MonthOfYear.from(month).monthName
    }

    fun getMonth() = MonthOfYear.from(binding.tvFilterMonth.text).month

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
        outState.putBoolean(INSTANCE_STATE_KEY_CLOSE_ICON, binding.ivCloseIcon.isVisible)
    }

    private fun onSaveYearInstanceState(outState: Bundle) {
        getYear()?.let { outState.putString(INSTANCE_STATE_KEY_YEAR_FILTER, it.toString()) }
    }

    private fun onSaveMonthInstanceState(outState: Bundle) {
        getMonth()?.let { outState.putString(INSTANCE_STATE_KEY_MONTH_FILTER, it.toString()) }
    }

}
