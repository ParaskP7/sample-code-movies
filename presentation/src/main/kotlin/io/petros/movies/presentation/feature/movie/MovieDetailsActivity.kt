package io.petros.movies.presentation.feature.movie

import android.os.Bundle
import com.f2prateek.dart.InjectExtra
import io.petros.movies.R
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity<MovieDetailsActivityViewModel>() {

    @InjectExtra lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv_movie_title.text = movie.title
    }

    /* CONTRACT */

    override fun constructContentView() = R.layout.activity_movie_details

    override fun constructViewModel() = MovieDetailsActivityViewModel::class.java

}
