/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/7/20 3:57 PM
 * Last modified 7/7/20 3:55 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.statusBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType


fun HomePage.setupUserInterface() {

    val topBarLayoutParams = homePageViewBinding.homepageTopBar.layoutParams as ConstraintLayout.LayoutParams
    topBarLayoutParams.height = homePageViewBinding.homepageTopBar.height + statusBarHeight(applicationContext)
    homePageViewBinding.homepageTopBar.layoutParams = topBarLayoutParams

    homePageViewBinding.homepageScrollingContentView.setPadding(0,
        homePageViewBinding.homepageTopBar.height + statusBarHeight(applicationContext),
        0, 0)

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