@file:Suppress("unused")

package io.petros.movies.android_utils

import io.petros.movies.utils.colon

/* EXTRA */

fun getExtraName(extraClass: Class<out Any>, extraName: String) =
    "${extraClass.canonicalName}${colon()}${colon()}$extraName"
