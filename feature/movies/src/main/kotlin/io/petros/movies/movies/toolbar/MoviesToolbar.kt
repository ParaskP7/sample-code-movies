package io.petros.movies.movies.toolbar

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.VisibleForTesting
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import io.petros.movies.movies.R
import io.petros.movies.movies.databinding.MoviesToolbarBinding
import io.petros.movies.utils.MonthOfYear
import timber.log.Timber

@Suppress("TooManyFunctions", "MemberVisibilityCanBePrivate")
class MoviesToolbar(
    ctx: Context,
    attrs: AttributeSet? = null
) : LinearLayout(ctx, attrs) {

    companion object {

        @VisibleForTesting const val INSTANCE_STATE_KEY_CLOSE_ICON = "CLOSE_ICON"
        @VisibleForTesting const val INSTANCE_STATE_KEY_YEAR_FILTER = "YEAR_FILTER"
        @VisibleForTesting const val INSTANCE_STATE_KEY_MONTH_FILTER = "MONTH_FILTER"

    }

    var callback: MoviesToolbarCallback? = null

    @VisibleForTesting val binding = MoviesToolbarBinding.inflate(LayoutInflater.from(context), this)

    init {
        initFilterIcon()
        initCloseIcon()
        initYearFilter()
        initMonthFilter()
    }

    private fun initFilterIcon() {
        binding.ivToolbarFilterIcon.setOnClickListener { onFilterIconClicked() }
    }

    private fun onFilterIconClicked() {
        showCloseIcon()
        showYear()
    }

    private fun initCloseIcon() {
        binding.ivToolbarCloseIcon.setOnClickListener { onCloseIconClicked() }
    }

    private fun onCloseIconClicked() {
        showFilterIcon()
        callback?.onCloseClicked()
    }

    private fun initYearFilter() {
        binding.tvToolbarFilterYear.setOnClickListener { callback?.onYearClicked() }
    }

    private fun initMonthFilter() {
        binding.tvToolbarFilterMonth.setOnClickListener { callback?.onMonthClicked() }
    }

    /* SHOW/HIDE */

    fun showFilterIcon() {
        binding.ivToolbarCloseIcon.isVisible = false
        binding.ivToolbarFilterIcon.isVisible = true
        hideYear()
        hideMonth()
    }

    fun showCloseIcon() {
        binding.ivToolbarFilterIcon.isVisible = false
        binding.ivToolbarCloseIcon.isVisible = true
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun showYear() {
        binding.tvToolbarFilterYear.isInvisible = false
        binding.tvToolbarFilterYear.text = context.getString(R.string.tvToolbarFilterYear)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun hideYear() {
        binding.tvToolbarFilterYear.isInvisible = true
        binding.tvToolbarFilterYear.text = context.getString(R.string.tvToolbarFilterYear)
    }

    fun showMonth() {
        binding.tvToolbarFilterMonth.isInvisible = false
        binding.tvToolbarFilterMonth.text = context.getString(R.string.tvToolbarFilterMonth)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun hideMonth() {
        binding.tvToolbarFilterMonth.isInvisible = true
        binding.tvToolbarFilterMonth.text = context.getString(R.string.tvToolbarFilterMonth)
    }

    /* YEAR */

    fun setYear(year: Int) {
        binding.tvToolbarFilterYear.text = year.toString()
    }

    fun restoreYear(year: Int) {
        showYear()
        showMonth()
        setYear(year)
    }

    @Suppress("SwallowedException")
    fun getYear(): Int? {
        val year = binding.tvToolbarFilterYear.text.toString()
        return try {
            year.toInt()
        } catch (nfe: NumberFormatException) {
            Timber.d("Cannot convert the year text to an integer, defaulting to null. [Year: $year]")
            null
        }
    }

    /* MONTH */

    fun setMonth(month: Int) {
        binding.tvToolbarFilterMonth.text = MonthOfYear.from(month).label
    }

    fun restoreMonth(month: Int) {
        showMonth()
        setMonth(month)
    }

    fun getMonth(): Int? {
        val monthOfYear = MonthOfYear.from(binding.tvToolbarFilterMonth.text)
        return if (monthOfYear != MonthOfYear.UNKNOWN_MONTH) monthOfYear.number else null
    }

    /* CONFIGURATION CHANGE */

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        onRestoreIconInstanceState(savedInstanceState)
        onRestoreYearInstanceState(savedInstanceState)
        onRestoreMonthInstanceState(savedInstanceState)
    }

    private fun onRestoreIconInstanceState(savedInstanceState: Bundle) {
        val closeIcon = savedInstanceState.getBoolean(INSTANCE_STATE_KEY_CLOSE_ICON)
        if (closeIcon) showCloseIcon()
    }

    private fun onRestoreYearInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.getString(INSTANCE_STATE_KEY_YEAR_FILTER)?.let { restoreYear(it.toInt()) }
    }

    private fun onRestoreMonthInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.getString(INSTANCE_STATE_KEY_MONTH_FILTER)?.let { restoreMonth(it.toInt()) }
    }

    fun onSaveInstanceState(outState: Bundle) {
        onSaveCloseIconInstanceState(outState)
        onSaveYearInstanceState(outState)
        onSaveMonthInstanceState(outState)
    }

    private fun onSaveCloseIconInstanceState(outState: Bundle) {
        outState.putBoolean(INSTANCE_STATE_KEY_CLOSE_ICON, binding.ivToolbarCloseIcon.isVisible)
    }

    private fun onSaveYearInstanceState(outState: Bundle) {
        getYear()?.let { outState.putString(INSTANCE_STATE_KEY_YEAR_FILTER, it.toString()) }
    }

    private fun onSaveMonthInstanceState(outState: Bundle) {
        getMonth()?.let { outState.putString(INSTANCE_STATE_KEY_MONTH_FILTER, it.toString()) }
    }

}
