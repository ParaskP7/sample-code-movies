package io.petros.movies.core.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ErrorViewHolder(itemView: View, action: () -> Unit) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { action() }
    }

}
