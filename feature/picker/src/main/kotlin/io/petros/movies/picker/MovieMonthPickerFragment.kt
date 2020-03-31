package io.petros.movies.picker

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.whiteelephant.monthpicker.MonthPickerDialog
import java.util.*

class MovieMonthPickerFragment : DialogFragment(), MonthPickerDialog.OnDateSetListener { // MRT

    companion object {

        const val TAG = "MovieMonthPickerFragment"

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        return MonthPickerDialog.Builder(context, this, year, month)
            .setActivatedMonth(month)
            .setTitle(context?.getString(R.string.dialog_picker_movie_month))
            .showMonthOnly()
            .build()
    }

    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
        (activity as? MovieMonthPickerFragmentCallback)?.onMonthPicked(selectedMonth)
    }

}
