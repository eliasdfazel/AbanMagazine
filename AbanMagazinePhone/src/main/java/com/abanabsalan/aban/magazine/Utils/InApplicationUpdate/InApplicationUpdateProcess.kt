/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/27/20 6:33 AM
 * Last modified 9/27/20 6:06 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.InApplicationUpdate

import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task

class InApplicationUpdateProcess (private val context: AppCompatActivity, private val rootView: ViewGroup) : InstallStateUpdatedListener {

    private val appUpdateManager: AppUpdateManager = AppUpdateManagerFactory.create(context)

    companion object {
        private const val IN_APP_UPDATE_REQUEST = 333
    }

    fun initialize() {

        appUpdateManager.registerListener(this@InApplicationUpdateProcess)

        val appUpdateInfo: Task<AppUpdateInfo> = appUpdateManager.appUpdateInfo
        appUpdateInfo.addOnSuccessListener { updateInfo ->

            if (updateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && updateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "New Update Is Available")

                if (!context.isFinishing) {

                    appUpdateManager.startUpdateFlowForResult(
                        updateInfo,
                        AppUpdateType.FLEXIBLE,
                        context,
                        InApplicationUpdateProcess.IN_APP_UPDATE_REQUEST
                    )

                }

            } else {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Latest Version Installed")

            }

        }.addOnFailureListener {

        }

    }

    override fun onStateUpdate(installState: InstallState) {

        when (installState.installStatus()) {
            InstallStatus.DOWNLOADING -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Downloading...")

            }
            InstallStatus.DOWNLOADED -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Downloaded")

                showCompleteConfirmation()

            }
            InstallStatus.INSTALLING -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Installing...")

            }
            InstallStatus.INSTALLED -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Installed")

                appUpdateManager.unregisterListener(this@InApplicationUpdateProcess)

            }
            InstallStatus.CANCELED -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Canceled")

            }
            InstallStatus.FAILED -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Failed")

            }
            InstallStatus.PENDING -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Pending")

            }
            InstallStatus.UNKNOWN -> {
                Log.d(this@InApplicationUpdateProcess.javaClass.simpleName, "Unknown")

            }
        }

    }

    private fun showCompleteConfirmation() {

        val snackbar = Snackbar.make(rootView, context.getString(R.string.inAppUpdateDescription),
            Snackbar.LENGTH_INDEFINITE)
        snackbar.setBackgroundTint(context.getColor(R.color.light_pink))
        snackbar.setTextColor(context.getColor(R.color.default_color_dark))
        snackbar.setActionTextColor(context.getColor(R.color.dark))
        snackbar.setAction(Html.fromHtml(context.getString(R.string.inAppUpdateAction), Html.FROM_HTML_MODE_LEGACY)) { view ->

            appUpdateManager.completeUpdate().addOnSuccessListener {


            }.addOnFailureListener {


            }

        }

        val view = snackbar.view
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        layoutParams.gravity = Gravity.BOTTOM
        view.layoutParams = layoutParams

        snackbar.show()

    }

}