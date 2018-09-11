package io.petros.movies.presentation

import android.content.Context
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.toast(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.getDimension(@DimenRes resource: Int) = resources.getDimensionPixelOffset(resource)
