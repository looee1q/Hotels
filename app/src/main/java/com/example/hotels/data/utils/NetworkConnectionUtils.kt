package com.example.hotels.data.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Checks if the device is connected to a network (WiFi, cellular, or Ethernet).
 *
 * @return `true` if the device is connected to a network, `false` otherwise.
 */
fun ConnectivityManager.isDeviceConnectedToNetwork(): Boolean {

    val capabilities = getNetworkCapabilities(activeNetwork)

    return when {
        capabilities == null -> false
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        else -> false
    }
}