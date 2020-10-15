/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 10/15/20 4:12 AM
 * Last modified 10/15/20 4:11 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.BlogContent

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

class PostsData (private val context: Context) {

    fun saveTotalPostsNumber(totalPostsNumber: Long) {

        val savePreferences = SavePreferences(context)

        savePreferences.savePreference("PostsInformation", "TotalPostsNumber", totalPostsNumber)

    }

    fun readTotalPostsNumber() : Long {

        val readPreferences = ReadPreferences(context)

        return readPreferences.readPreference("PostsInformation", "TotalPostsNumber", System.currentTimeMillis())
    }

}