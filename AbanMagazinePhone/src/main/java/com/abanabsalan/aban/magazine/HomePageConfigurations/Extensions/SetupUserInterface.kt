/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/19/20 7:51 PM
 * Last modified 7/19/20 6:46 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Display.statusBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType

fun HomePage.setupUserInterface() {

    val topBarLayoutParams = homePageViewBinding.homepageTopBar.layoutParams as ConstraintLayout.LayoutParams
    topBarLayoutParams.height = homePageViewBinding.homepageTopBar.height + statusBarHeight(applicationContext)
    homePageViewBinding.homepageTopBar.layoutParams = topBarLayoutParams

    val bottomBarBlurViewLayoutParams = homePageViewBinding.blurViewBottomBar.layoutParams as ConstraintLayout.LayoutParams
    bottomBarBlurViewLayoutParams.height = navigationBarHeight(applicationContext)
    homePageViewBinding.blurViewBottomBar.layoutParams = bottomBarBlurViewLayoutParams

    homePageViewBinding.homepageScrollingContentView.setPadding(0,
        homePageViewBinding.homepageTopBar.height + statusBarHeight(applicationContext),
        0, 0)

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.statusBarColor = getColor(R.color.default_color_light_transparent)
            window.navigationBarColor = getColor(R.color.default_color_light_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_light_transparent))
            homePageViewBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_light_transparent))

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.default_color_dark_transparent)
            window.navigationBarColor = getColor(R.color.default_color_dark_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))
            homePageViewBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))

        }
    }

}