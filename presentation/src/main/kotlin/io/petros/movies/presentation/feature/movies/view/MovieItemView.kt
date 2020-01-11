package io.petros.movies.presentation.feature.movies.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import io.petros.movies.R
import io.petros.movies.data.extensions.getDimension
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.extensions.displayImage
import io.petros.movies.presentation.extensions.inflate
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieItemView : CardView {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        inflate(R.layout.item_movie)
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
        iv_movie_backdrop.displayImage(movie.backdrop)
        tv_movie_title.text = movie.title
        tv_movie_release_date.text = movie.releaseDate()
        tv_movie_vote.text = movie.vote()
    }

    fun bindCallback(movie: Movie, callback: MovieCallback) {
        val sharedElementMovie = SharedElementMovie(movie, iv_movie_backdrop)
        setOnClickListener { callback.onClick(sharedElementMovie) }
    }

}
