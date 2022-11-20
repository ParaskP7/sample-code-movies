@file:Suppress("unused", "Filename", "MaximumLineLength")

package io.petros.movies.androidutils

import io.petros.movies.utils.colon

/* EXTRA */

fun getExtraName(extraClass: Class<out Any>, extraName: String) =
    "${extraClass.canonicalName}${colon()}${colon()}$extraName"
