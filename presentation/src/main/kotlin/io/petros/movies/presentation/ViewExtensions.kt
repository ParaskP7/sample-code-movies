package io.petros.movies.presentation

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes resource: Int): View = View.inflate(context, resource, this)
