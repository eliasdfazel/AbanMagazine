/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/25/20 3:55 AM
 * Last modified 7/25/20 3:55 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Theme

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

object ThemeType {
    const val ThemeLight: Int = 0
    const val ThemeDark: Int = 1
}

class OverallTheme (private val context: Context) {

    companion object {
        var LastActivity: String? = null
    }

    private val savePreferences: SavePreferences = SavePreferences(context)
    private val readPreferences: ReadPreferences = ReadPreferences(context)

    /**
     * ThemeType
     * 0 = Light
     * 1 = Dark
     **/
    fun checkThemeLightDark() : Int {

        return readPreferences.readPreference(".Theme", "OverallTheme", ThemeType.ThemeLight)
    }

    fun saveThemeLightDark(themeType: Int) {

        savePreferences.savePreference(".Theme", "OverallTheme", themeType)
    }

}