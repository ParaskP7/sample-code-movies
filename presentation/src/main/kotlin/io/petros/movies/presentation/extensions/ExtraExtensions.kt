package io.petros.movies.presentation.extensions

import io.petros.movies.utils.colon

// TODO: Move file to `android-utils` module

fun getExtraName(extraClass: Class<out Any>, extraName: String) =
    "${extraClass.canonicalName}${colon()}${colon()}$extraName"
