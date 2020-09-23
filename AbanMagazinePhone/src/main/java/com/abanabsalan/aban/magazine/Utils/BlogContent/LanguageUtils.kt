/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/23/20 10:40 AM
 * Last modified 9/23/20 10:00 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.BlogContent

import android.content.Context
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences


class LanguageUtils {

    companion object {
        var SelectedLanguage: String = PostsDataParameters.Language.Persian
    }

    /**
     * RTL Language: True
     **/
    fun checkRtl(aString: String): Boolean {

        return if (aString.isNotBlank() && aString.isNotEmpty()) {
            val firstChar = aString[0]
            firstChar.toInt() in 0x590..0x6ff
        } else {
             false
        }
    }

    fun selectedBaseDomain(context: Context) : String {

        val readPreferences: ReadPreferences = ReadPreferences(context)

        return when (readPreferences.readPreference("UserPreferences", "Language", PostsDataParameters.Language.English)) {
            PostsDataParameters.Language.Persian -> {

                GeneralEndpoints.GeneralEndpointsAddressDotIr

            }
            PostsDataParameters.Language.English -> {

                GeneralEndpoints.GeneralEndpointsAddressDotCom

            }
            else -> {

                GeneralEndpoints.GeneralEndpointsAddressDotCom

            }
        }

    }

    fun selectedLanguage(context: Context) : String {

        val readPreferences: ReadPreferences = ReadPreferences(context)

        return when (readPreferences.readPreference("UserPreferences", "Language", PostsDataParameters.Language.English)) {
            PostsDataParameters.Language.Persian -> {

                PostsDataParameters.Language.Persian

            }
            PostsDataParameters.Language.English -> {

                PostsDataParameters.Language.English

            }
            else -> {

                PostsDataParameters.Language.English

            }
        }

    }

    fun saveSelectedLanguage(context: Context, selectedLanguage: String) {

        LanguageUtils.SelectedLanguage = selectedLanguage

        val savePreferences: SavePreferences = SavePreferences(context)

        savePreferences.savePreference("UserPreferences", "Language", selectedLanguage)

    }

}