/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/24/21 9:53 AM
 * Last modified 2/24/21 9:38 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions

import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.OnlineStore
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Display.statusBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType

fun OnlineStore.setupOnlineStoreUserInterface() {

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.statusBarColor = getColor(R.color.default_color_light_transparent)
            window.navigationBarColor = getColor(R.color.default_color_light_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.light))

            onlineStoreLayoutBinding.rootView.setBackgroundColor(getColor(R.color.light))

            onlineStoreLayoutBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_light_transparent))
            onlineStoreLayoutBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_light_transparent))

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.default_color_dark_transparent)
            window.navigationBarColor = getColor(R.color.default_color_dark_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.dark))

            onlineStoreLayoutBinding.rootView.setBackgroundColor(getColor(R.color.dark))

            onlineStoreLayoutBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))
            onlineStoreLayoutBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))

        }
    }

    val topBarLayoutManager = onlineStoreLayoutBinding.blurViewTopBar.layoutParams as ConstraintLayout.LayoutParams
    topBarLayoutManager.height = statusBarHeight(applicationContext)
    onlineStoreLayoutBinding.blurViewTopBar.layoutParams = topBarLayoutManager

    val bottomBarLayoutManager = onlineStoreLayoutBinding.blurViewBottomBar.layoutParams as ConstraintLayout.LayoutParams
    bottomBarLayoutManager.height = navigationBarHeight(applicationContext)
    onlineStoreLayoutBinding.blurViewBottomBar.layoutParams = bottomBarLayoutManager

}