@file:Suppress("Filename")

package io.petros.movies.core.image.glide

import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

/* IMAGE VIEW */

fun ShapeableImageView.displayImage(url: String?, placeholder: Int = android.R.color.darker_gray) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .fitCenter()
        .into(this)
}
