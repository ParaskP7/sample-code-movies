package io.petros.movies.presentation.feature.common.list.holder

import android.support.v7.widget.RecyclerView
import android.view.View

class ErrorViewHolder(itemView: View, action: () -> Unit) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { action() }
    }

}
