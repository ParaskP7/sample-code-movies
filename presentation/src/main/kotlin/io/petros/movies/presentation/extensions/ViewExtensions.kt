package io.petros.movies.presentation.extensions

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/* VIEW GROUP */

fun ViewGroup.inflate(@LayoutRes resource: Int): View = View.inflate(context, resource, this)
