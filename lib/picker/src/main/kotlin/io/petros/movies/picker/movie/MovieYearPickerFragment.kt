package io.petros.movies.picker.movie

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import io.petros.movies.lib.picker.R
import io.petros.movies.picker.lib.PickerDialog
import java.util.*

class MovieYearPickerFragment : DialogFragment(),
    PickerDialog.OnDateSetListener {

    private var onYearPicked: ((Int) -> Unit)? = null

    fun onYearPicked(onYearPicked: (Int) -> Unit): MovieYearPickerFragment {
        this.onYearPicked = onYearPicked
        return this
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        return PickerDialog.Builder(context, year, 0, this)
            .setActivatedYear(year)
            .setMaxYear(year + EXTRA_YEARS)
            .setTitle(requireContext().getString(R.string.dlgMovieYear))
            .showYearOnly()
            .build()
    }

    override fun onDateSet(selectedMonth: Int, selectedYear: Int) {
        onYearPicked?.invoke(selectedYear)
    }

    fun show(manager: FragmentManager) {
        show(manager, TAG)
    }

    companion object {

        private const val TAG = "MovieYearPickerFragment"
        private const val EXTRA_YEARS = 10

    }

}
