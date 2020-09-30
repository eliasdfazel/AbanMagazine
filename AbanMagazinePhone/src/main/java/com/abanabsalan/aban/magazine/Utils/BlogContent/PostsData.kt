/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/30/20 8:00 AM
 * Last modified 9/30/20 7:57 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.BlogContent

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

class PostsData (private val context: Context) {

    fun saveTotalPostsNumber(totalPostsNumber: Int) {

        val savePreferences = SavePreferences(context)

        savePreferences.savePreference("PostsInformation", "TotalPostsNumber", totalPostsNumber)

    }

    fun readTotalPostsNumber() : Int {

        val readPreferences = ReadPreferences(context)

        return readPreferences.readPreference("PostsInformation", "TotalPostsNumber", Int.MAX_VALUE)
    }

}