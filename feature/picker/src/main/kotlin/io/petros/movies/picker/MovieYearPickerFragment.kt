package io.petros.movies.picker

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.whiteelephant.monthpicker.MonthPickerDialog
import java.util.*

class MovieYearPickerFragment : DialogFragment(), MonthPickerDialog.OnDateSetListener { // MRT

    companion object {

        const val TAG = "MovieYearPickerFragment"
        private const val EXTRA_YEARS = 10

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        return MonthPickerDialog.Builder(context, this, year, 0)
            .setActivatedYear(year)
            .setMaxYear(year + EXTRA_YEARS)
            .setTitle(context?.getString(R.string.dialog_picker_movie_year))
            .showYearOnly()
            .build()
    }

    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
        (activity as? MovieYearPickerFragmentCallback)?.onYearPicked(selectedYear)
    }

}
