package io.petros.movies.app

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import io.petros.movies.R
import io.petros.movies.core.activity.BaseActivity
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.databinding.AppActivityBinding

@Suppress("GoogleAppIndexingApiWarning")
class AppActivity : BaseActivity<AppActivityBinding>() {

    override val binding by viewBinding(AppActivityBinding::inflate)

    @Suppress("LateinitUsage") private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.moviesFragment), binding.ctrApp)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() = if (this::appBarConfiguration.isInitialized) {
        findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
    } else {
        false
    }

    /* CONTRACT */

    override fun constructContentView() = binding.root

}
