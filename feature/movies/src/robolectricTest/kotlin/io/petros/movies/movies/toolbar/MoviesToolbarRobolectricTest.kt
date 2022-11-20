package io.petros.movies.movies.toolbar

import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.androidtest.context.TestContextProvider.context
import io.petros.movies.androidtest.runner.CustomRobolectricTestRunner
import io.petros.movies.feature.movies.R
import io.petros.movies.utils.MonthOfYear
import org.junit.Test
import org.junit.runner.RunWith
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isNull
import strikt.assertions.isTrue

@RunWith(CustomRobolectricTestRunner::class)
class MoviesToolbarRobolectricTest {

    private val context = context()
    private val callbackMock = mockk<MoviesToolbarCallback>()

    private val testedClass = MoviesToolbar(context).also {
        it.callback = callbackMock
    }

    /* FILTER ICON */

    @Test
    fun `when movies toolbar is instantiated, then a click listener is set on the filter icon`() {
        expect { that(testedClass.binding.ivToolbarFilterIcon.hasOnClickListeners()).isTrue() }
    }

    @Test
    fun `when filter icon is clicked, then on filter clicked is triggered on the callback`() {
        testedClass.binding.ivToolbarFilterIcon.performClick()

        verify { callbackMock.onFilterClicked() }
    }

    /* CLOSE ICON */

    @Test
    fun `when movies toolbar is instantiated, then a click listener is set on the close icon`() {
        expect { that(testedClass.binding.ivToolbarCloseIcon.hasOnClickListeners()).isTrue() }
    }

    @Test
    fun `when close icon is clicked, then on close clicked is triggered on the callback`() {
        testedClass.binding.ivToolbarCloseIcon.performClick()

        verify { callbackMock.onCloseClicked() }
    }

    /* FILTER YEAR */

    @Test
    fun `when movies toolbar is instantiated, then a click listener is set on the filter year`() {
        expect { that(testedClass.binding.tvToolbarFilterYear.hasOnClickListeners()).isTrue() }
    }

    @Test
    fun `when filter year is clicked, then on year clicked is triggered on the callback`() {
        testedClass.binding.tvToolbarFilterYear.performClick()

        verify { callbackMock.onYearClicked() }
    }

    /* FILTER MONTH */

    @Test
    fun `when movies toolbar is instantiated, then a click listener is set on the filter month`() {
        expect { that(testedClass.binding.tvToolbarFilterMonth.hasOnClickListeners()).isTrue() }
    }

    @Test
    fun `when filter month is clicked, then on month clicked is triggered on the callback`() {
        testedClass.binding.tvToolbarFilterMonth.performClick()

        verify { callbackMock.onMonthClicked() }
    }

    /* SHOW/HIDE */

    @Test
    fun `when show filter icon is triggered, then filter icon is shown`() {
        testedClass.showCloseIcon()
        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isFalse() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isTrue() }

        testedClass.showFilterIcon()

        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isTrue() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isFalse() }
    }

    @Test
    fun `when show close icon is triggered, then close icon is shown`() {
        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isTrue() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isFalse() }

        testedClass.showCloseIcon()

        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isFalse() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isTrue() }
    }

    @Test
    fun `given no year, when show year is triggered, then the default year is shown`() {
        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }

        testedClass.showYear()

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
    }

    @Test
    fun `given a year, when show year is triggered, then the given year is shown`() {
        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
        val year = "2020"

        testedClass.showYear(year)

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
        expect { that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(year) }
    }

    @Test
    fun `when hide year is triggered, then the year gets hidden`() {
        val year = "2020"
        testedClass.showYear(year)
        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
        expect { that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(year) }

        testedClass.hideYear()

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
    }

    @Test
    fun `given no month, when show month is triggered, then the default month is shown`() {
        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }

        testedClass.showMonth()

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }
    }

    @Test
    fun `given a month, when show month is triggered, then the month year is shown`() {
        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }
        val month = MonthOfYear.JANUARY.label

        testedClass.showMonth(month)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
        expect { that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(month) }
    }

    @Test
    fun `when hide month is triggered, then the month gets hidden`() {
        val month = MonthOfYear.JANUARY.label
        testedClass.showMonth(month)
        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
        expect { that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(month) }

        testedClass.hideMonth()

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }
    }

    /* YEAR */

    @Test
    fun `when set year is triggered, then the correct year label is set on the filter year`() {
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
        val year = 2020

        testedClass.setYear(year)

        expect { that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(year.toString()) }
    }

    @Test
    fun `when set year is triggered, then year is shown`() {
        testedClass.hideYear()

        testedClass.setYear(2020)

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
    }

    @Test
    fun `when set year is triggered, then month is shown`() {
        testedClass.hideMonth()

        testedClass.setYear(2020)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
    }

    @Test
    fun `when set year is triggered, then year is set`() {
        val previousYear = 2019
        val currentYear = 2020
        testedClass.setMonth(previousYear)

        testedClass.setYear(currentYear)

        expect { that(testedClass.getYear()).isEqualTo(currentYear) }
    }

    @Test
    fun `when get year is triggered, then the correct year number is returned`() {
        expect { that(testedClass.getYear()).isNull() }
        val year = 2020
        testedClass.setYear(year)

        val result = testedClass.getYear()

        expect { that(result).isEqualTo(year) }
    }

    @Test
    fun `when clean year is triggered, then the year gets hidden`() {
        val year = "2020"
        testedClass.showYear(year)
        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
        expect { that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(year) }

        testedClass.clearYear()

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
    }

    @Test
    fun `when clean year is triggered, then the month gets hidden`() {
        val month = MonthOfYear.JANUARY.label
        testedClass.showMonth(month)
        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
        expect { that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(month) }

        testedClass.clearYear()

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }
    }

    /* MONTH */

    @Test
    fun `when set month is triggered, then the correct month label is set on the filter month`() {
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text)
                .isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }
        val month = MonthOfYear.JANUARY

        testedClass.setMonth(month.number)

        expect { that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(month.label) }
    }

    @Test
    fun `when set month is triggered, then month is shown`() {
        testedClass.hideMonth()

        testedClass.setMonth(MonthOfYear.JANUARY.number)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
    }

    @Test
    fun `when set month is triggered, then month is set`() {
        val previousMonth = MonthOfYear.JANUARY.number
        val currentMonth = MonthOfYear.FEBRUARY.number
        testedClass.setMonth(previousMonth)

        testedClass.setMonth(currentMonth)

        expect { that(testedClass.getMonth()).isEqualTo(currentMonth) }
    }

    @Test
    fun `when get month is triggered, then the correct month number is returned`() {
        expect { that(testedClass.getMonth()).isNull() }
        val month = MonthOfYear.JANUARY
        testedClass.setMonth(month.number)

        val result = testedClass.getMonth()

        expect { that(result).isEqualTo(month.number) }
    }

    /* CONFIGURATION CHANGE - RESTORE - CLOSE ICON */

    @Test
    fun `given close icon state is false, when restore instance state, then filter icon is shown`() {
        testedClass.binding.ivToolbarCloseIcon.performClick()
        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isTrue() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isFalse() }
        val savedInstanceState = Bundle().also {
            it.putBoolean(MoviesToolbar.INSTANCE_STATE_KEY_CLOSE_ICON, false)
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isTrue() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isFalse() }
    }

    @Test
    fun `given close icon state is true, when restore instance state, then close icon is shown`() {
        testedClass.binding.ivToolbarCloseIcon.performClick()
        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isTrue() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isFalse() }
        val savedInstanceState = Bundle().also {
            it.putBoolean(MoviesToolbar.INSTANCE_STATE_KEY_CLOSE_ICON, true)
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isFalse() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isTrue() }
    }

    /* CONFIGURATION CHANGE - RESTORE - YEAR FILTER */

    @Test
    fun `given year filter state is null, when restore instance state, then year is not shown`() {
        testedClass.hideYear()
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_YEAR_FILTER, null)
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isTrue() }
    }

    @Test
    fun `given year filter state is not null, when restore instance state, then year is shown`() {
        testedClass.hideYear()
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_YEAR_FILTER, 2019.toString())
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
    }

    @Test
    fun `given year filter state is null, when restore instance state, then month is not shown`() {
        testedClass.hideMonth()
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_YEAR_FILTER, null)
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isTrue() }
    }

    @Test
    fun `given year filter state is not null, when restore instance state, then month is shown`() {
        testedClass.hideMonth()
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_YEAR_FILTER, 2020.toString())
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
    }

    @Test
    fun `given year filter state is null, when restore instance state, then year is not set`() {
        val previousYear = 2019
        testedClass.setYear(previousYear)
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_YEAR_FILTER, null)
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.getYear()).isEqualTo(previousYear) }
    }

    @Test
    fun `given year filter state is not null, when restore instance state, then year is set`() {
        val previousYear = 2019
        val currentYear = 2020
        testedClass.setYear(previousYear)
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_YEAR_FILTER, currentYear.toString())
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.getYear()).isEqualTo(currentYear) }
    }

    /* CONFIGURATION CHANGE - RESTORE - MONTH FILTER */

    @Test
    fun `given month filter state is null, when restore instance state, then month is not shown`() {
        testedClass.hideMonth()
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_MONTH_FILTER, null)
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isTrue() }
    }

    @Test
    fun `given month filter state is not null, when restore instance state, then month is shown`() {
        testedClass.hideMonth()
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_MONTH_FILTER, MonthOfYear.FEBRUARY.number.toString())
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
    }

    @Test
    fun `given month filter state is null, when restore instance state, then month is not set`() {
        val previousMonth = MonthOfYear.JANUARY.number
        testedClass.setMonth(previousMonth)
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_MONTH_FILTER, null)
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.getMonth()).isEqualTo(previousMonth) }
    }

    @Test
    fun `given month filter state is not null, when restore instance state, then month is set`() {
        val previousMonth = MonthOfYear.JANUARY.number
        val currentMonth = MonthOfYear.FEBRUARY.number
        testedClass.setMonth(previousMonth)
        val savedInstanceState = Bundle().also {
            it.putString(MoviesToolbar.INSTANCE_STATE_KEY_MONTH_FILTER, currentMonth.toString())
        }

        testedClass.onRestoreInstanceState(savedInstanceState)

        expect { that(testedClass.getMonth()).isEqualTo(currentMonth) }
    }

    /* CONFIGURATION CHANGE - SAVE */

    @Test
    fun `given year is not set, when save instance state, then don't save close icon state`() {
        val closeIconState = false
        testedClass.binding.ivToolbarCloseIcon.isVisible = closeIconState
        val savedInstanceState = Bundle()

        testedClass.onSaveInstanceState(savedInstanceState)

        expect {
            that(savedInstanceState.getBoolean(MoviesToolbar.INSTANCE_STATE_KEY_CLOSE_ICON))
                .isEqualTo(closeIconState)
        }
    }

    @Test
    fun `given year is set, when save instance state, then do save close icon state`() {
        val closeIconState = true
        testedClass.binding.ivToolbarCloseIcon.isVisible = closeIconState
        val savedInstanceState = Bundle()
        testedClass.setYear(2020)

        testedClass.onSaveInstanceState(savedInstanceState)

        expect {
            that(savedInstanceState.getBoolean(MoviesToolbar.INSTANCE_STATE_KEY_CLOSE_ICON))
                .isEqualTo(closeIconState)
        }
    }

    @Test
    fun `when save instance state, then save year filter state`() {
        val yearFilterState = 2020
        testedClass.setYear(yearFilterState)
        val savedInstanceState = Bundle()

        testedClass.onSaveInstanceState(savedInstanceState)

        expect {
            that(savedInstanceState.getString(MoviesToolbar.INSTANCE_STATE_KEY_YEAR_FILTER))
                .isEqualTo(yearFilterState.toString())
        }
    }

    @Test
    fun `when save instance state, then save month filter state`() {
        val monthFilterState = MonthOfYear.JANUARY.number
        testedClass.setMonth(monthFilterState)
        val savedInstanceState = Bundle()

        testedClass.onSaveInstanceState(savedInstanceState)

        expect {
            that(savedInstanceState.getString(MoviesToolbar.INSTANCE_STATE_KEY_MONTH_FILTER))
                .isEqualTo(monthFilterState.toString())
        }
    }

}
