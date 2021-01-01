/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 1/1/21 5:24 AM
 * Last modified 1/1/21 5:23 AM
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


        return readPreferences.readPreference("CacheMechanism", "CachedTime", 0).toLong()
    }

}