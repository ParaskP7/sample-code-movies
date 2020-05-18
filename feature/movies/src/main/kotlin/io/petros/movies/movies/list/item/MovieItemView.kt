package io.petros.movies.movies.list.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import com.google.android.material.card.MaterialCardView
import io.petros.movies.android_utils.getDimension
import io.petros.movies.core.image.glide.displayImage
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movies.R
import io.petros.movies.movies.databinding.MovieItemViewBinding

class MovieItemView(ctx: Context) : MaterialCardView(ctx) {

    @VisibleForTesting val binding = MovieItemViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        initView()
    }

    private fun initView() {
        val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftMargin = context.getDimension(R.dimen.cdItemHorizontalMargin)
        lp.rightMargin = context.getDimension(R.dimen.cdItemHorizontalMargin)
        lp.bottomMargin = context.getDimension(R.dimen.cdItemVerticalMargin)
        layoutParams = lp
    }

    fun bind(movie: Movie) {
        bindMovie(movie)
    }

    private fun bindMovie(movie: Movie) {
        binding.ivItemBackdrop.displayImage(movie.backdrop)
        binding.tvItemTitle.text = movie.title
        binding.tvItemReleaseDate.text = movie.releaseDate()
        binding.tvItemVote.text = movie.vote()
    }

    fun bindCallback(movie: Movie, callback: MovieItemCallback?) {
        setOnClickListener { callback?.onClick(movie) }
    }

}
