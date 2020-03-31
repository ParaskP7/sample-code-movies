package io.petros.movies.movie_details.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.appbar.AppBarLayout
import io.petros.movies.movie_details.databinding.MovieDetailsToolbarBinding

class MovieDetailsToolbar : AppBarLayout {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        MovieDetailsToolbarBinding.inflate(LayoutInflater.from(context), this)
    }

}
