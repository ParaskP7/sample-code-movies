package io.petros.movies.picker

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.whiteelephant.monthpicker.MonthPickerDialog
import io.petros.movies.lib.picker.R
import java.util.*

class MovieYearPickerFragment(
    private val onYearPicked: (Int) -> Unit,
) : DialogFragment(),
    MonthPickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        return MonthPickerDialog.Builder(context, this, year, 0)
            .setActivatedYear(year)
            .setMaxYear(year + EXTRA_YEARS)
            .setTitle(context?.getString(R.string.dlgMovieYear))
            .showYearOnly()
            .build()
    }

    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
        onYearPicked(selectedYear)
    }

    fun show(manager: FragmentManager) {
        show(manager, TAG)
    }

    companion object {

        private const val TAG = "MovieYearPickerFragment"
        private const val EXTRA_YEARS = 10

    }

}
