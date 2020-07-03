/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/3/20 2:19 PM
 * Last modified 7/3/20 2:16 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Preferences

import android.content.Context
import androidx.preference.PreferenceManager

class ReadPreferences (private val context: Context) {

    fun readPreference(PreferenceName: String?, KEY: String?, defaultVALUE: String?): String? {
        return context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE).getString(KEY, defaultVALUE)
    }

    fun readPreference(PreferenceName: String?, KEY: String?, defaultVALUE: Int): Int {
        return context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE).getInt(KEY, defaultVALUE)
    }

    fun readPreference(PreferenceName: String?, KEY: String?, defaultVALUE: Long): Long {
        return context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE).getLong(KEY, defaultVALUE)
    }

    fun readPreference(PreferenceName: String?, KEY: String?, defaultVALUE: Float): Float {
        return context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE).getFloat(KEY, defaultVALUE)
    }

    fun readPreference(PreferenceName: String?, KEY: String?, defaultVALUE: Boolean): Boolean {
        return context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE).getBoolean(KEY, defaultVALUE)
    }

    fun readDefaultPreference(KEY: String?, defaultVALUE: Int): Int {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY, defaultVALUE)
    }

    fun readDefaultPreference(KEY: String?, defaultVALUE: String?): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY, defaultVALUE)
    }

    fun readDefaultPreference(KEY: String?, defaultVALUE: Boolean): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY, defaultVALUE)
    }

}