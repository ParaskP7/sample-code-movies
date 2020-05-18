package io.petros.movies.app

import android.os.Bundle
import io.petros.movies.R
import io.petros.movies.core.activity.BaseActivity
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.databinding.AppActivityBinding

class AppActivity : BaseActivity() {

    private val binding by viewBinding(AppActivityBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    /* CONTRACT */

    override fun constructContentView() = binding.root

}
