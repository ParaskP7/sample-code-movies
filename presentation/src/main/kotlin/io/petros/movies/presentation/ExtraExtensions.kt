package io.petros.movies.presentation

import io.petros.movies.domain.colon

fun getExtraName(extraClass: Class<out Any>, extraName: String) =
    "${extraClass.canonicalName}${colon()}${colon()}$extraName"
