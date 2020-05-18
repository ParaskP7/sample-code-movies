package io.petros.movies.movies.toolbar

import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import io.mockk.mockk
import io.mockk.verify
import io.petros.movies.android_test.app.TestApp
import io.petros.movies.android_test.context.TestContextProvider.context
import io.petros.movies.movies.R
import io.petros.movies.utils.MonthOfYear
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isNull
import strikt.assertions.isTrue

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
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
    fun `when filter icon is clicked, then filter icon gets invisible while close icon gets visible`() {
        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isTrue() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isFalse() }

        testedClass.binding.ivToolbarFilterIcon.performClick()

        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isFalse() }
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isTrue() }
    }

    @Test
    fun `when filter icon is clicked, then show year`() {
        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }

        testedClass.binding.ivToolbarFilterIcon.performClick()

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
    }

    /* CLOSE ICON */

    @Test
    fun `when movies toolbar is instantiated, then a click listener is set on the close icon`() {
        expect { that(testedClass.binding.ivToolbarCloseIcon.hasOnClickListeners()).isTrue() }
    }

    @Test
    fun `when close icon is clicked, then close icon gets invisible while filter icon gets visible`() {
        testedClass.binding.ivToolbarFilterIcon.performClick()
        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isTrue() }
        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isFalse() }

        testedClass.binding.ivToolbarCloseIcon.performClick()

        expect { that(testedClass.binding.ivToolbarCloseIcon.isVisible).isFalse() }
        expect { that(testedClass.binding.ivToolbarFilterIcon.isVisible).isTrue() }
    }

    @Test
    fun `when close icon is clicked, then hide year`() {
        testedClass.binding.ivToolbarFilterIcon.performClick()
        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }

        testedClass.binding.ivToolbarCloseIcon.performClick()

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
    }

    @Test
    fun `when close icon is clicked, then hide month`() {
        testedClass.showMonth()
        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }

        testedClass.binding.ivToolbarCloseIcon.performClick()

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isTrue() }
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }
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

    /* YEAR */

    @Test
    fun `when set year is triggered, then the correct year label is set on the filter year`() {
        expect {
            that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(context.getString(R.string.tvToolbarFilterYear))
        }
        val year = 2020

        testedClass.setYear(year)

        expect { that(testedClass.binding.tvToolbarFilterYear.text).isEqualTo(year.toString()) }
    }

    @Test
    fun `when restore year is triggered, then year is shown`() {
        testedClass.hideYear()

        testedClass.restoreYear(2020)

        expect { that(testedClass.binding.tvToolbarFilterYear.isInvisible).isFalse() }
    }

    @Test
    fun `when restore year is triggered, then monht is shown`() {
        testedClass.hideMonth()

        testedClass.restoreYear(2020)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
    }

    @Test
    fun `when restore year is triggered, then year is set`() {
        val previousYear = 2019
        val currentYear = 2020
        testedClass.setMonth(previousYear)

        testedClass.restoreYear(currentYear)

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

    /* MONTH */

    @Test
    fun `when set month is triggered, then the correct month label is set on the filter month`() {
        expect {
            that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(context.getString(R.string.tvToolbarFilterMonth))
        }
        val month = MonthOfYear.JANUARY

        testedClass.setMonth(month.number)

        expect { that(testedClass.binding.tvToolbarFilterMonth.text).isEqualTo(month.label) }
    }

    @Test
    fun `when restore month is triggered, then month is shown`() {
        testedClass.hideMonth()

        testedClass.restoreMonth(MonthOfYear.JANUARY.number)

        expect { that(testedClass.binding.tvToolbarFilterMonth.isInvisible).isFalse() }
    }

    @Test
    fun `when restore month is triggered, then month is set`() {
        val previousMonth = MonthOfYear.JANUARY.number
        val currentMonth = MonthOfYear.FEBRUARY.number
        testedClass.setMonth(previousMonth)

        testedClass.restoreMonth(currentMonth)

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
    fun `given close icon state is false, when restore instance state, then on filter icon is not clicked`() {
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
    fun `given close icon state is true, when restore instance state, then on filter icon is clicked`() {
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
    fun `when save instance state, then save close icon state`() {
        val closeIconState = true
        testedClass.binding.ivToolbarCloseIcon.isVisible = closeIconState
        val savedInstanceState = Bundle()

        testedClass.onSaveInstanceState(savedInstanceState)

        expect { that(savedInstanceState.getBoolean(MoviesToolbar.INSTANCE_STATE_KEY_CLOSE_ICON)).isEqualTo(closeIconState) }
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
