package io.petros.movies.core.list.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import io.petros.movies.core.databinding.ErrorItemViewBinding

class ErrorItemView(ctx: Context) : FrameLayout(ctx) {

    init {
        ErrorItemViewBinding.inflate(LayoutInflater.from(context), this)
        initView()
    }

    private fun initView() {
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}
