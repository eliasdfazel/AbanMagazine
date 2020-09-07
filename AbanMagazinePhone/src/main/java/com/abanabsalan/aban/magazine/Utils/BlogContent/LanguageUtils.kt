/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/7/20 4:38 AM
 * Last modified 9/7/20 4:37 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.BlogContent

import android.content.Context
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences


class LanguageUtils {

    /**
     * False For RTL Language
     **/
    fun checkRtl(aString: String): Boolean {

        return if (aString.isNotBlank() && aString.isNotEmpty()) {
            val firstChar = aString[0]
            firstChar.toInt() in 0x590..0x6ff
        } else {
             false
        }
    }

    fun selectedLanguage(context: Context) : String {

        val readPreferences: ReadPreferences = ReadPreferences(context)

        return when (readPreferences.readPreference("UserPreferences", "Language", PostsDataParameters.Language.Persian)) {
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

}