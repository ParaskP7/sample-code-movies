package io.petros.movies.presentation

import android.widget.ImageView
import androidx.core.content.ContextCompat
import io.petros.movies.presentation.ui.glide.GlideApp

/* IMAGE VIEW */

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
