package io.petros.movies.app

import io.petros.movies.core.activity.BaseActivity
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.databinding.AppActivityBinding

class AppActivity : BaseActivity() {

    private val binding by viewBinding(AppActivityBinding::inflate)

    /* CONTRACT */

    override fun constructContentView() = binding.root

}
