package io.petros.movies.presentation

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

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
