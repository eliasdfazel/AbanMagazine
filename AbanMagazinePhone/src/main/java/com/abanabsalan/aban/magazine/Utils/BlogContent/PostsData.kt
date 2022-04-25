/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.BlogContent

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

class PostsData (private val context: Context) {

    fun saveWebsiteDataDate(totalPostsNumber: Long) {

        val savePreferences = SavePreferences(context)

        savePreferences.savePreference("PostsInformation", "WebsiteDataDate", totalPostsNumber)

    }

    fun readWebsiteDataDate() : Long {

        val readPreferences = ReadPreferences(context)

        return readPreferences.readPreference("PostsInformation", "WebsiteDataDate", System.currentTimeMillis())
    }

}