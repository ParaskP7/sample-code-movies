package io.petros.movies.presentation.feature.movie

import android.os.Bundle
import com.f2prateek.dart.InjectExtra
import io.petros.movies.R
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.displayImage
import io.petros.movies.presentation.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity<MovieDetailsActivityViewModel>() { // MET

    @InjectExtra lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMovie(movie)
    }

    private fun initMovie(movie: Movie) {
        iv_movie_backdrop.displayImage(movie.backdrop)
        tv_movie_title.text = movie.title
        tv_movie_release_date.text = movie.releaseDate()
        tv_movie_vote.text = movie.vote()
        tv_movie_overview.text = movie.overview
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movie_details

    override fun constructViewModel() = MovieDetailsActivityViewModel::class.java

}
