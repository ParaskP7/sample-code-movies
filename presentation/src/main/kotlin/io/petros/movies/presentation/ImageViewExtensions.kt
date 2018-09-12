package io.petros.movies.presentation

import android.support.v4.content.ContextCompat
import android.widget.ImageView
import io.petros.movies.presentation.ui.glide.GlideApp

fun ImageView.displayImage(url: String?, placeholder: Int = android.R.color.darker_gray) {
    if (url != null) {
        GlideApp.with(this)
            .load(url)
            .placeholder(placeholder)
            .fitCenter()
            .into(this)
    } else {
        setImageDrawable(ContextCompat.getDrawable(context, placeholder))
    }
}
