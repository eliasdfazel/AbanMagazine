/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
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
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.Scopes.ActivityScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.offline_indicator.view.*
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

    private var offlineIndicator: View

    init {
        connectivityManager.registerDefaultNetworkCallback(this@NetworkConnectionListener)

        offlineIndicator = LayoutInflater.from(appCompatActivity).inflate(R.layout.offline_indicator, rootView, false)
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

                    rootView.removeView(offlineIndicator)
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

                    rootView.addView(offlineIndicator)

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