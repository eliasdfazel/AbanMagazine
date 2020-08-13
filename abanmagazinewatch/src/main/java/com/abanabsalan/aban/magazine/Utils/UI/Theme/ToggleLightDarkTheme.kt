/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/10/20 2:01 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Theme

import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
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

            homePage.homePageViewBinding.optionMenus.setAnimation(R.raw.lady_settings_dark)

        }
    }

}

fun toggleLightDarkThemePostView(singlePostView: SinglePostView) {

    OverallTheme.LastActivity = singlePostView.javaClass.simpleName

    when (singlePostView.overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            singlePostView.window.navigationBarColor = singlePostView.getColor(R.color.light)

            singlePostView.postsViewUiBinding.rootView.setBackgroundColor(singlePostView.getColor(R.color.light))

            singlePostView.postsViewUiBinding.postTitle.setTextColor(singlePostView.getColor(R.color.darker))

            singlePostView.postsViewUiBinding.postRecyclerView.setBackgroundColor(singlePostView.getColor(R.color.light))

            singlePostView.postsViewUiBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(singlePostView.getColor(R.color.light_transparent))

        }
        ThemeType.ThemeDark -> {

            singlePostView.window.navigationBarColor = singlePostView.getColor(R.color.dark)

            singlePostView.postsViewUiBinding.rootView.setBackgroundColor(singlePostView.getColor(R.color.dark))

            singlePostView.postsViewUiBinding.postTitle.setTextColor(singlePostView.getColor(R.color.lighter))

            singlePostView.postsViewUiBinding.postRecyclerView.setBackgroundColor(singlePostView.getColor(R.color.dark))

            singlePostView.postsViewUiBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(singlePostView.getColor(R.color.dark_transparent))

        }
    }

}