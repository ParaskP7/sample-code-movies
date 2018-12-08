package io.petros.movies.presentation.feature.common.list.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ErrorViewHolder(itemView: View, action: () -> Unit) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { action() }
    }

}
