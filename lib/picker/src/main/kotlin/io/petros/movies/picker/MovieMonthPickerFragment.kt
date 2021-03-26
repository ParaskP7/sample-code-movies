package io.petros.movies.picker

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.whiteelephant.monthpicker.MonthPickerDialog
import io.petros.movies.lib.picker.R
import java.util.*

class MovieMonthPickerFragment(
    private val onMonthPicked: (Int) -> Unit,
) : DialogFragment(),
    MonthPickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        return MonthPickerDialog.Builder(context, this, year, month)
            .setActivatedMonth(month)
            .setTitle(requireContext().getString(R.string.dlgMovieMonth))
            .showMonthOnly()
            .build()
    }

    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
        onMonthPicked(selectedMonth)
    }

    fun show(manager: FragmentManager) {
        show(manager, TAG)
    }

    companion object {

        private const val TAG = "MovieMonthPickerFragment"

    }

}
