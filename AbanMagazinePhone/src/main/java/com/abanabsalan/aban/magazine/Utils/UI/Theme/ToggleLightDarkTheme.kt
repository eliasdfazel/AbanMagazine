/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 10:03 AM
 * Last modified 4/25/22, 10:01 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Theme

import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R

fun toggleLightDarkThemeHomePage(homePage: HomePage) {

    OverallTheme.LastActivity = homePage.javaClass.simpleName

    when (homePage.overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            homePage.window.statusBarColor = homePage.getColor(R.color.default_color_light_transparent)
            homePage.window.navigationBarColor = homePage.getColor(R.color.default_color_light_transparent)
            homePage.window.decorView.setBackgroundColor(homePage.getColor(R.color.light))

            homePage.homePageViewBinding.rootView.setBackgroundColor(homePage.getColor(R.color.light))

            homePage.homePageViewBinding.blurViewTopBar.setOverlayColor(homePage.getColor(R.color.default_color_light_transparent))
            homePage.homePageViewBinding.blurViewBottomBar.setOverlayColor(homePage.getColor(R.color.default_color_light_transparent))

            homePage.homePageViewBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(homePage.getColor(R.color.default_color_light_transparent))
            homePage.homePageViewBinding.searchPopupInclude.searchesBlurView.setOverlayColor(homePage.getColor(R.color.default_color_light_transparent))

            homePage.homePageViewBinding.featuredPostsTextView.setTextColor(homePage.getColor(R.color.darker))
            homePage.homePageViewBinding.newestPostsTextView.setTextColor(homePage.getColor(R.color.darker))

            homePage.homePageViewBinding.productShowcaseTextView.setTextColor(homePage.getColor(R.color.darker))

            homePage.homePageViewBinding.optionMenus.setAnimation(R.raw.lady_settings_light)

        }
        ThemeType.ThemeDark -> {

            homePage.window.statusBarColor = homePage.getColor(R.color.default_color_dark_transparent)
            homePage.window.navigationBarColor = homePage.getColor(R.color.default_color_dark_transparent)
            homePage.window.decorView.setBackgroundColor(homePage.getColor(R.color.dark))

            homePage.homePageViewBinding.rootView.setBackgroundColor(homePage.getColor(R.color.dark))

            homePage.homePageViewBinding.blurViewTopBar.setOverlayColor(homePage.getColor(R.color.default_color_dark_transparent))
            homePage.homePageViewBinding.blurViewBottomBar.setOverlayColor(homePage.getColor(R.color.default_color_dark_transparent))

            homePage.homePageViewBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(homePage.getColor(R.color.default_color_dark_transparent))
            homePage.homePageViewBinding.searchPopupInclude.searchesBlurView.setOverlayColor(homePage.getColor(R.color.default_color_dark_transparent))

            homePage.homePageViewBinding.featuredPostsTextView.setTextColor(homePage.getColor(R.color.lighter))
            homePage.homePageViewBinding.newestPostsTextView.setTextColor(homePage.getColor(R.color.lighter))

            homePage.homePageViewBinding.productShowcaseTextView.setTextColor(homePage.getColor(R.color.lighter))

            homePage.homePageViewBinding.optionMenus.setAnimation(R.raw.lady_settings_dark)

        }
    }

}