/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Network

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.Scopes.ActivityScope
import com.abanabsalan.aban.magazine.databinding.OfflineIndicatorBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject

interface NetworkConnectionListenerInterface {
    fun networkAvailable()
    fun networkLost()
}

@ActivityScope
class NetworkConnectionListener @Inject constructor (private var appCompatActivity: AppCompatActivity,
                                                     private var rootView: ConstraintLayout,
                                                     private var networkCheckpoint: NetworkCheckpoint) :  ConnectivityManager.NetworkCallback() {

    var networkConnectionListenerInterface: NetworkConnectionListenerInterface? = null

    private val connectivityManager = appCompatActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var offlineIndicator: OfflineIndicatorBinding

    init {
        connectivityManager.registerDefaultNetworkCallback(this@NetworkConnectionListener)

        offlineIndicator = OfflineIndicatorBinding.inflate(appCompatActivity.layoutInflater, rootView, false)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)

        appCompatActivity.runOnUiThread {

            networkConnectionListenerInterface?.let {
                it.networkAvailable()
            }

            Handler(Looper.getMainLooper()).postDelayed({
                if (networkCheckpoint.networkConnection()) {
                    Log.d(this@NetworkConnectionListener.javaClass.simpleName, "Network Available")

                    rootView.removeView(offlineIndicator.root)
                }
            }, 555)
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)

        appCompatActivity.runOnUiThread {

            networkConnectionListenerInterface?.let {
                it.networkLost()
            }

            Handler(Looper.getMainLooper()).postDelayed({
                if (!networkCheckpoint.networkConnection()) {
                    Log.d(this@NetworkConnectionListener.javaClass.simpleName, "Network Lost")

                    rootView.addView(offlineIndicator.root)

                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        Glide.with(appCompatActivity)
                            .asGif()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .load(R.drawable.no_internet_connection)
                            .into(offlineIndicator.offlineWait)

                        offlineIndicator.offlineWait.setOnClickListener {

                            appCompatActivity.startActivityForResult(
                                Intent(Settings.ACTION_WIFI_SETTINGS)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                                NetworkSettingCallback.WifiSetting
                            )

                            appCompatActivity.finish()
                        }

                    }, 555)

                }
            }, 555)
        }
    }

    fun unregisterDefaultNetworkCallback() {

        connectivityManager.unregisterNetworkCallback(this@NetworkConnectionListener)
    }
}