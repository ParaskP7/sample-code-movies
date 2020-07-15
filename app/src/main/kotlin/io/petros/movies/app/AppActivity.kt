package io.petros.movies.app

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import io.petros.movies.R
import io.petros.movies.android_utils.network.NetworkLiveEvent
import io.petros.movies.core.activity.BaseActivity
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.databinding.AppActivityBinding

@Suppress("GoogleAppIndexingApiWarning")
class AppActivity : BaseActivity<AppActivityBinding>() {

    override val binding by viewBinding(AppActivityBinding::inflate)

    @Suppress("LateinitUsage") private lateinit var appBarConfiguration: AppBarConfiguration

    private var snackbar: Snackbar? = null

    private val connectivity = Observer<Boolean> { isConnected ->
        if (isConnected) {
            snackbar?.dismiss()
        } else {
            snackbar = Snackbar
                .make(binding.ctrApp, R.string.sbNoConnectivity, Snackbar.LENGTH_INDEFINITE)
            snackbar?.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.moviesFragment), binding.ctrApp)
        setupActionBarWithNavController(navController, appBarConfiguration)
        initObservers()
    }

    private fun initObservers() {
        NetworkLiveEvent.observe(this, connectivity)
    }

    override fun onSupportNavigateUp() = if (this::appBarConfiguration.isInitialized) {
        findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
    } else {
        false
    }

    override fun onResume() {
        super.onResume()
        NetworkLiveEvent.checkConnectivity()
    }

    override fun onDestroy() {
        snackbar?.dismiss()
        snackbar = null
        super.onDestroy()
    }

    /* CONTRACT */

    override fun constructContentView() = binding.root

}
