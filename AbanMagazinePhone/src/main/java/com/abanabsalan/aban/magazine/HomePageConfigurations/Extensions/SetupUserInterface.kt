/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/3/20 9:31 AM
 * Last modified 7/3/20 9:15 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType

fun HomePage.setupUserInterface() {

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.statusBarColor = getColor(R.color.default_color_light)
            window.navigationBarColor = getColor(R.color.default_color_light)

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.light))

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.default_color_darker)
            window.navigationBarColor = getColor(R.color.default_color_darker)

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.dark))

        }
    }

}