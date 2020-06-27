/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/27/20 9:22 AM
 * Last modified 6/27/20 8:24 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

class Checkpoint (private val context: Context) {

    fun networkConnection(): Boolean {

        var networkAvailable = false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        if (connectivityManager != null) {

            val activeNetwork: Network? = connectivityManager.activeNetwork
            val networkCapabilities: NetworkCapabilities? = connectivityManager.getNetworkCapabilities(activeNetwork)

            if (networkCapabilities != null) {

                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {

                        networkAvailable = true

                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {

                        networkAvailable = true

                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {

                        networkAvailable = true

                    }
                }

            }

        }

        return networkAvailable
    }
}