package io.petros.movies.presentation.extensions

import io.petros.movies.domain.extensions.colon

fun getExtraName(extraClass: Class<out Any>, extraName: String) =
    "${extraClass.canonicalName}${colon()}${colon()}$extraName"
