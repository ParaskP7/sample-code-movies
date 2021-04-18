package io.petros.movies.app

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import io.petros.movies.R
import io.petros.movies.android_utils.network.NetworkLiveEvent
import io.petros.movies.core.activity.BaseActivity
import io.petros.movies.core.view_binding.viewBinding
import io.petros.movies.databinding.AppActivityBinding

@Suppress("GoogleAppIndexingApiWarning", "TooManyFunctions")
class AppActivity : BaseActivity<AppActivityBinding>() {

    override val binding by viewBinding(AppActivityBinding::inflate)

    private val configuration by lazy { AppBarConfiguration(setOf(R.id.moviesFragment), binding.ctrApp) }

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
        initActionBar()
        initDrawer()
        initObservers()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(navController, configuration)
    }

    private fun initDrawer() {
        initComposeMenuItem(binding.navigationView.menu.findItem(R.id.drawerMenuItemJetpackCompose))
    }

    private fun initComposeMenuItem(menuItem: MenuItem) {
        val switch = menuItem.actionView as SwitchMaterial
        initComposeMenuItemSwitchState(switch)
        initComposeMenuItemSwitchListener(switch)
    }

    @Suppress("ForbiddenComment", "UnusedPrivateMember")
    private fun initComposeMenuItemSwitchState(@Suppress("UNUSED_PARAMETER") switch: SwitchMaterial) {
        // TODO: Implement settings.
    }

    @Suppress("FunctionMaxLength", "ForbiddenComment", "UnusedPrivateMember")
    private fun initComposeMenuItemSwitchListener(@Suppress("UNUSED_PARAMETER") switch: SwitchMaterial) {
        // TODO: Implement settings.
    }

    private fun initObservers() {
        NetworkLiveEvent.observe(this, connectivity)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment)
        .navigateUp(configuration)

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
