/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/21/20 3:38 AM
 * Last modified 8/21/20 3:38 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.AccountManager

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

class UserInformation (private val context: Context) {

    fun saveUserInformation(userEmailAddress: String, userLabelName: String) {

        val savePreferences = SavePreferences(context)

        savePreferences.savePreference("UserInformation", "Email", userEmailAddress)
        savePreferences.savePreference("UserInformation", "Username", userLabelName)

    }

    fun userSignedIn() : Boolean {

        return (ReadPreferences(context).readPreference("UserInformation", "Email", "Unknown") != "Unknown")
    }

}