package io.petros.movies.android_utils.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object ConnectivityUtil {

    fun isConnected(connectivityManager: ConnectivityManager) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        isConnectedMAndAfter(connectivityManager)
    } else {
        isConnectedBeforeM(connectivityManager)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isConnectedMAndAfter(connectivityManager: ConnectivityManager): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let { capabilities ->
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        } ?: false
    }

    @Suppress("DEPRECATION")
    private fun isConnectedBeforeM(connectivityManager: ConnectivityManager) =
        connectivityManager.activeNetworkInfo?.isConnected ?: false

}
