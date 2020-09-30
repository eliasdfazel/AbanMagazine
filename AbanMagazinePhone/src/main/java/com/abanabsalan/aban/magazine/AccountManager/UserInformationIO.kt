/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/30/20 6:38 AM
 * Last modified 9/30/20 5:49 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.AccountManager

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

class UserInformationIO(private val context: Context) {

    fun saveUserInformation(userEmailAddress: String) {

        val savePreferences = SavePreferences(context)

        savePreferences.savePreference("UserInformation", "Email", userEmailAddress)

    }

    fun getUserAccountName() : String? {

        return ReadPreferences(context).readPreference("UserInformation", "Email", null)
    }

    fun userSignedIn() : Boolean {

        return (ReadPreferences(context).readPreference("UserInformation", "Email", "Unknown") != "Unknown")
    }

}