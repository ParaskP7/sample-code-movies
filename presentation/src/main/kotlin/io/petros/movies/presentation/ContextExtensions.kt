package io.petros.movies.presentation

import android.content.Context
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.StringRes

fun Context.toast(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.getDimension(@DimenRes resource: Int) = resources.getDimensionPixelOffset(resource)
