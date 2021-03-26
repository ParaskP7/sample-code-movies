@file:Suppress("unused")

package io.petros.movies.android_utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/* RES */

fun Context.getLong(@IntegerRes id: Int): Long = resources.getInteger(id).toLong()

fun Context.getDimension(@DimenRes resource: Int) = resources.getDimensionPixelOffset(resource)

/* TOAST */

fun Context.toast(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.toast(@StringRes message: Int) {
    requireContext().toast(message)
}
