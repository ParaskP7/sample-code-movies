package io.petros.movies.core.image.glide

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

/* IMAGE VIEW */

fun ShapeableImageView.displayImage(url: String?, placeholder: Int = android.R.color.darker_gray) {
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
