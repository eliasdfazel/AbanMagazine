/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/7/20 3:42 PM
 * Last modified 7/7/20 3:38 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType


fun HomePage.setupUserInterface() {

    homePageViewBinding.homepageScrollingContentView.setPadding(0, homePageViewBinding.homepageTopBar.height, 0, 0)

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.statusBarColor = getColor(R.color.default_color_light_transparent)
            window.navigationBarColor = getColor(R.color.default_color_light_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.blurView.setOverlayColor(getColor(R.color.default_color_light_transparent))

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.default_color_dark_transparent)
            window.navigationBarColor = getColor(R.color.default_color_dark_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.blurView.setOverlayColor(getColor(R.color.default_color_dark_transparent))

        }
    }

}