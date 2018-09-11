package io.petros.movies.data

import android.content.Context
import android.support.annotation.IntegerRes

/* CONTEXT */

fun Context.getLong(@IntegerRes id: Int): Long = resources.getInteger(id).toLong()
