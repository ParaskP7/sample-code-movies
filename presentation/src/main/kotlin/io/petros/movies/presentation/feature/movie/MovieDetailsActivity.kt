package io.petros.movies.presentation.feature.movie

import android.os.Bundle
import android.view.View
import io.petros.movies.databinding.MovieDetailsActivityBinding
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.BaseActivity
import io.petros.movies.presentation.feature.movie.navigator.MovieDetailsActivityLauncher.Companion.getMovie
import io.petros.movies.presentation.image.glide.displayImage

class MovieDetailsActivity : BaseActivity() { // MET

    private val movie: Movie by lazy { getMovie(intent) }

    @Suppress("LateinitUsage") private lateinit var binding: MovieDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMovie(movie)
    }

    private fun initMovie(movie: Movie) {
        binding.ivMovieBackdrop.displayImage(movie.backdrop)
        binding.tvMovieTitle.text = movie.title
        binding.tvMovieReleaseDate.text = movie.releaseDate()
        binding.tvMovieVote.text = movie.vote()
        binding.tvMovieOverview.text = movie.overview
    }

    /* CONTRACT */

    override fun constructContentView(): View? {
        binding = MovieDetailsActivityBinding.inflate(layoutInflater)
        return binding.root
    }

}
