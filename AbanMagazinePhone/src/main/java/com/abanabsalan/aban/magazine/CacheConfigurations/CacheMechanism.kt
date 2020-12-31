/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/31/20 6:20 AM
 * Last modified 12/31/20 6:04 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CacheConfigurations

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

class CacheMechanism (private val context: Context) {

    companion object {
        private const val DefaultTimeToLive: Long = 1000 * 60 * 60 * 72
    }

    fun checkTimeToLive() : Boolean {

        return  ((System.currentTimeMillis() - retrieveCachedTime()) > DefaultTimeToLive)
    }

    fun storeCachedTime(nowTime: Long = System.currentTimeMillis()) {

        val savePreferences = SavePreferences(context)

        savePreferences.savePreference("CacheMechanism", "CachedTime", nowTime)

    }

    fun retrieveCachedTime() : Long {

        val readPreferences = ReadPreferences(context)


        return readPreferences.readPreference("CacheMechanism", "CachedTime", System.currentTimeMillis())
    }

}