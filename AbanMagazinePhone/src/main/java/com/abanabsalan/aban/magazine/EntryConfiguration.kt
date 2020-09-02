/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/2/20 3:24 AM
 * Last modified 9/2/20 2:43 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.Utils.UI.NotifyUser.SnackbarActionHandlerInterface
import com.abanabsalan.aban.magazine.Utils.UI.NotifyUser.SnackbarBuilder
import com.abanabsalan.aban.magazine.databinding.EntryConfigurationViewBinding
import com.google.android.material.snackbar.Snackbar

class EntryConfiguration : AppCompatActivity() {

    private lateinit var entryConfigurationViewBinding: EntryConfigurationViewBinding

    companion object {
        const val PermissionRequestCode: Int = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryConfigurationViewBinding = EntryConfigurationViewBinding.inflate(layoutInflater)
        setContentView(entryConfigurationViewBinding.root)

        runtimePermission()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissionsList: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults)

        when (requestCode) {
            EntryConfiguration.PermissionRequestCode -> {

                if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CHANGE_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {

                    startActivity(Intent(applicationContext, HomePage::class.java),
                        ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle())

                } else {

                    SnackbarBuilder(applicationContext).show (
                        rootView = entryConfigurationViewBinding.rootView,
                        messageText= getString(R.string.permissionMessage),
                        messageDuration = Snackbar.LENGTH_INDEFINITE,
                        actionButtonText = R.string.grantPermission,
                        snackbarActionHandlerInterface = object : SnackbarActionHandlerInterface {

                            override fun onActionButtonClicked(snackbar: Snackbar) {
                                super.onActionButtonClicked(snackbar)

                                runtimePermission()

                            }

                        }
                    )

                }

            }
        }

    }

    private fun runtimePermission() {

        val permissionsList = arrayListOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.VIBRATE
        )

        requestPermissions(
            permissionsList.toTypedArray(),
            EntryConfiguration.PermissionRequestCode
        )

    }

}