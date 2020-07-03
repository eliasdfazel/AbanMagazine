/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/3/20 9:31 AM
 * Last modified 7/3/20 9:09 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Theme

import android.content.Context

object ThemeType {
    const val ThemeLight: Int = 0
    const val ThemeDark: Int = 1
}

class OverallTheme (private val context: Context) {

    /**
     * True = Light
     * False =
     **/
    fun checkThemeLightDark() : Int {

        return ThemeType.ThemeLight
    }

}