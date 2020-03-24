package io.petros.movies.presentation.feature.common.toolbar

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.AppBarLayout
import io.petros.movies.R
import io.petros.movies.android_utils.inflate

class MovieDetailsToolbar : AppBarLayout {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        inflate(R.layout.toolbar_movie_details)
    }

}
