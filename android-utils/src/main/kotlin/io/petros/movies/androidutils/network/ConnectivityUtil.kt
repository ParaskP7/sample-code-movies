package io.petros.movies.androidutils.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object ConnectivityUtil {

    fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let { capabilities ->
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        } ?: false
    }

}
