package io.petros.movies.picker.movie

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import io.petros.movies.lib.picker.R
import io.petros.movies.picker.lib.PickerDialog
import java.util.*

class MovieMonthPickerFragment : DialogFragment(),
    PickerDialog.OnDateSetListener {

    private var onMonthPicked: ((Int) -> Unit)? = null

    fun onMonthPicked(onMonthPicked: (Int) -> Unit): MovieMonthPickerFragment {
        this.onMonthPicked = onMonthPicked
        return this
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        return PickerDialog.Builder(context, year, month, this)
            .setActivatedMonth(month)
            .setTitle(requireContext().getString(R.string.dlgMovieMonth))
            .showMonthOnly()
            .build()
    }

    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
        onMonthPicked?.invoke(selectedMonth)
    }

    fun show(manager: FragmentManager) {
        show(manager, TAG)
    }

    companion object {

        private const val TAG = "MovieMonthPickerFragment"

    }

}
