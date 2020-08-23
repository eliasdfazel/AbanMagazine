/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/23/20 9:07 AM
 * Last modified 8/23/20 8:09 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.System

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.abanabsalan.aban.magazine.Utils.Extensions.capitalizeFirstChar

class SystemInformation(private val context: Context) {

    object Requirements {
        const val RequiredMemory: Long = 4000000000
    }
    object CPU_MODELS {
        const val Qualcomm: String = "qcom"
    }

    /**
     * Check Device Information & Apply Lite Preferences For Specific Device Model
     **/
    fun checkDeviceInformation() {

        when (getCpuModel()) {
            CPU_MODELS.Qualcomm -> {

            }
            else -> {

                if (!checkRequiredMemory()
                    && !context.getFileStreamPath(".LitePreferenceCheckpoint").exists()) {

                    /*
                    *
                    */
                }
            }
        }
    }

    fun getCpuModel() : String {

        return Build.HARDWARE
    }

    fun getDeviceManufacturer() : String {

        return Build.MANUFACTURER
    }

    fun checkRequiredMemory() : Boolean {

        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?

        val memoryInfo = ActivityManager.MemoryInfo()

        return if (activityManager != null) {

            activityManager.getMemoryInfo(memoryInfo)

            //Return
            memoryInfo.totalMem >= Requirements.RequiredMemory || !memoryInfo.lowMemory

        } else {

            false

        }
    }

    fun getDeviceName(): String {

        val manufacturer = Build.MANUFACTURER

        val model = Build.MODEL

        return if (model.startsWith(manufacturer)) {

            (model).capitalizeFirstChar()

        } else {

            (manufacturer).capitalizeFirstChar() + " " + model.capitalizeFirstChar()

        }

    }

    fun getCountryIso(): String {

        var countryISO = "Undefined"

        try {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            countryISO = telephonyManager.simCountryIso

            if (countryISO.length < 2) {
                countryISO = "Undefined"
            }

        } catch (e: Exception) {
            e.printStackTrace()

            countryISO = "Undefined"
        }

        return countryISO
    }

}