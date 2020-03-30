package io.petros.movies.presentation.feature.movies.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.cardview.widget.CardView
import io.petros.movies.R
import io.petros.movies.android_utils.getDimension
import io.petros.movies.databinding.MovieItemViewBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.image.glide.displayImage

class MovieItemView : CardView {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    @VisibleForTesting val binding = MovieItemViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        initView()
    }

    private fun initView() {
        val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftMargin = context.getDimension(R.dimen.item_movie_margin)
        lp.rightMargin = context.getDimension(R.dimen.item_movie_margin)
        lp.bottomMargin = context.getDimension(R.dimen.item_movie_space)
        layoutParams = lp
        radius = context.getDimension(R.dimen.item_movie_radius).toFloat()
        cardElevation = context.getDimension(R.dimen.item_movie_elevation).toFloat()
    }

    fun bind(movie: Movie) {
        bindMovie(movie)
    }

    private fun bindMovie(movie: Movie) {
        binding.ivMovieBackdrop.displayImage(movie.backdrop)
        binding.tvMovieTitle.text = movie.title
        binding.tvMovieReleaseDate.text = movie.releaseDate()
        binding.tvMovieVote.text = movie.vote()
    }

    fun bindCallback(movie: Movie, callback: MovieCallback?) {
        val sharedElementMovie = SharedElementMovie(movie, binding.ivMovieBackdrop)
        setOnClickListener { callback?.onClick(sharedElementMovie) }
    }

}
