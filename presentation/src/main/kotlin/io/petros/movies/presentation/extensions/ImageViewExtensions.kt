package io.petros.movies.presentation.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

// TODO: Move file to `android-utils` module

/* IMAGE VIEW */

fun ImageView.displayImage(url: String?, placeholder: Int = android.R.color.darker_gray) {
    if (url != null) {
        Glide.with(this)
            .load(url)
            .placeholder(placeholder)
            .fitCenter()
            .into(this)
    } else {
        setImageDrawable(ContextCompat.getDrawable(context, placeholder))
    }
}
