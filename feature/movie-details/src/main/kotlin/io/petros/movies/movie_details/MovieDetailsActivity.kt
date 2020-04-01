package io.petros.movies.movie_details

import android.os.Bundle
import android.view.View
import io.petros.movies.core.activity.BaseActivity
import io.petros.movies.core.image.glide.displayImage
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.movie_details.databinding.MovieDetailsActivityBinding
import io.petros.movies.movie_details.navigator.MovieDetailsActivityLauncher.Companion.getMovie

class MovieDetailsActivity : BaseActivity() { // MET

    private val movie: Movie by lazy { getMovie(intent) }

    @Suppress("LateinitUsage") private lateinit var binding: MovieDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMovie(movie)
    }

    private fun initMovie(movie: Movie) {
        binding.ivBackdrop.displayImage(movie.backdrop)
        binding.tvTitle.text = movie.title
        binding.tvReleaseDate.text = movie.releaseDate()
        binding.tvVote.text = movie.vote()
        binding.tvOverview.text = movie.overview
    }

    /* CONTRACT */

    override fun constructContentView(): View? {
        binding = MovieDetailsActivityBinding.inflate(layoutInflater)
        return binding.root
    }

}
