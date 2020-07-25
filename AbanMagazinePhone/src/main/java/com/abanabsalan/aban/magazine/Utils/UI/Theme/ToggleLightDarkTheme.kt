/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/25/20 3:35 AM
 * Last modified 7/25/20 3:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Theme

import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.R

fun toggleLightDarkThemeHomePage(homePage: HomePage) {

    when (homePage.overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            homePage.window.statusBarColor = homePage.getColor(R.color.default_color_light_transparent)
            homePage.window.navigationBarColor = homePage.getColor(R.color.default_color_light_transparent)
            homePage.window.decorView.setBackgroundColor(homePage.getColor(R.color.light))

            homePage.homePageViewBinding.rootView.setBackgroundColor(homePage.getColor(R.color.light))

            homePage.homePageViewBinding.blurViewTopBar.setOverlayColor(homePage.getColor(R.color.default_color_light_transparent))
            homePage.homePageViewBinding.blurViewBottomBar.setOverlayColor(homePage.getColor(R.color.default_color_light_transparent))

            homePage.homePageViewBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(homePage.getColor(R.color.default_color_light_transparent))

            homePage.homePageViewBinding.featuredPostsTextView.setTextColor(homePage.getColor(R.color.darker))
            homePage.homePageViewBinding.newestPostsTextView.setTextColor(homePage.getColor(R.color.darker))

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

            homePage.homePageViewBinding.featuredPostsTextView.setTextColor(homePage.getColor(R.color.lighter))
            homePage.homePageViewBinding.newestPostsTextView.setTextColor(homePage.getColor(R.color.lighter))

            homePage.homePageViewBinding.optionMenus.setAnimation(R.raw.lady_settings_dark)

        }
    }

}

fun toggleLightDarkThemePostView(postView: PostView) {

    when (postView.overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            postView.window.navigationBarColor = postView.getColor(R.color.light)

            postView.postsViewUiBinding.rootView.setBackgroundColor(postView.getColor(R.color.light))

            postView.postsViewUiBinding.postTitle.setTextColor(postView.getColor(R.color.darker))

            postView.postsViewUiBinding.postRecyclerView.setBackgroundColor(postView.getColor(R.color.light))

            postView.postsViewUiBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(postView.getColor(R.color.light_transparent))

        }
        ThemeType.ThemeDark -> {

            postView.window.navigationBarColor = postView.getColor(R.color.dark)

            postView.postsViewUiBinding.rootView.setBackgroundColor(postView.getColor(R.color.dark))

            postView.postsViewUiBinding.postTitle.setTextColor(postView.getColor(R.color.lighter))

            postView.postsViewUiBinding.postRecyclerView.setBackgroundColor(postView.getColor(R.color.dark))

            postView.postsViewUiBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(postView.getColor(R.color.dark_transparent))

        }
    }

}