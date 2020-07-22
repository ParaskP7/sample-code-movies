package io.petros.movies.android_utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import io.petros.movies.android_utils.SingleLiveEvent
import timber.log.Timber

object NetworkLiveEvent : SingleLiveEvent<Boolean>() {

    @Suppress("LateinitUsage") private lateinit var connectivityManager: ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        private val netIds = mutableSetOf<Int>()

        override fun onAvailable(network: Network) {
            netIds.add(network.toString().toInt())
            Timber.v("On network available. [Network: $network, Net Ids: $netIds]")
            postValue(true)
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            netIds.remove(network.toString().toInt())
            if (netIds.isEmpty()) {
                Timber.v("On network lost. [Network: $network]")
                postValue(false)
            } else {
                Timber.v("On network lost, but more networks exist. [Network: $network, Net Ids: $netIds]")
            }
            super.onLost(network)
        }
    }

    /* INIT */

    fun init(context: Context) {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /* LIFECYCLE */

    override fun onActive() {
        super.onActive()
        registerCallback()
    }

    private fun registerCallback() {
        if (this::connectivityManager.isInitialized) {
            val networkRequest = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        }
    }

    override fun onInactive() {
        removeCallback()
        super.onInactive()
    }

    private fun removeCallback() {
        if (this::connectivityManager.isInitialized) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    /* CHECK */

    fun checkConnectivity() {
        if (this::connectivityManager.isInitialized) {
            value = ConnectivityUtil.isConnected(connectivityManager)
        }
    }

    fun isConnected(): Boolean {
        return if (this::connectivityManager.isInitialized) {
            ConnectivityUtil.isConnected(connectivityManager)
        } else {
            false
        }
    }

}
