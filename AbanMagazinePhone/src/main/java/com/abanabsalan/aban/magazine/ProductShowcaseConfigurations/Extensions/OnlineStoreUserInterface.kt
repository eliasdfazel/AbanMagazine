/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/26/21 9:10 AM
 * Last modified 2/26/21 9:10 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions

import android.graphics.drawable.LayerDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.OnlineStore
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
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

            val onlineStoreOptions = getDrawable(R.drawable.online_store_options_background) as LayerDrawable
            onlineStoreOptions.findDrawableByLayerId(R.id.temporaryBackground).setTint(getColor(R.color.dark_transparent_high))
            onlineStoreOptions.findDrawableByLayerId(R.id.temporaryForeground).setTint(getColor(R.color.lighter))

            onlineStoreLayoutBinding.searchProductsView.background = onlineStoreOptions

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.default_color_dark_transparent)
            window.navigationBarColor = getColor(R.color.default_color_dark_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.dark))

            onlineStoreLayoutBinding.rootView.setBackgroundColor(getColor(R.color.dark))

            onlineStoreLayoutBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))
            onlineStoreLayoutBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))

            val onlineStoreOptions = getDrawable(R.drawable.online_store_options_background) as LayerDrawable
            onlineStoreOptions.findDrawableByLayerId(R.id.temporaryBackground).setTint(getColor(R.color.light_transparent_high))
            onlineStoreOptions.findDrawableByLayerId(R.id.temporaryForeground).setTint(getColor(R.color.darker))

            onlineStoreLayoutBinding.searchProductsView.background = onlineStoreOptions

        }
    }

    val topBarLayoutManager = onlineStoreLayoutBinding.blurViewTopBar.layoutParams as ConstraintLayout.LayoutParams
    topBarLayoutManager.height = statusBarHeight(applicationContext)
    onlineStoreLayoutBinding.blurViewTopBar.layoutParams = topBarLayoutManager

    val bottomBarLayoutManager = onlineStoreLayoutBinding.blurViewBottomBar.layoutParams as ConstraintLayout.LayoutParams
    bottomBarLayoutManager.height = navigationBarHeight(applicationContext)
    onlineStoreLayoutBinding.blurViewBottomBar.layoutParams = bottomBarLayoutManager

    onlineStoreLayoutBinding.allProductsRecyclerView.setPadding(0, statusBarHeight(applicationContext), 0, 0)

    onlineStoreLayoutBinding.onlineStoreScrollingContentView.setPadding(0,
        statusBarHeight(applicationContext),
        0,
        navigationBarHeight(applicationContext) + DpToInteger(applicationContext, 71))

}