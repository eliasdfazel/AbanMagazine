/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.WebView

import android.webkit.JavascriptInterface

/**
 * Android
 **/
class WebInterface (private val context: BuiltInWebView) {

    /**
     * ThemeType
     * 0 = Light
     * 1 = Dark
     **/
    @JavascriptInterface
    fun getThemeColor() : Int {

        return context.overallTheme.checkThemeLightDark()
    }

}