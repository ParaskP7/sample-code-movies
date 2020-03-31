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
