/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Extensions

import android.os.Build
import android.view.View
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType

fun FavoritesPostsView.setupUserInterface() {

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.statusBarColor = getColor(R.color.light)
            window.navigationBarColor = getColor(R.color.light)

            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            if (Build.VERSION.SDK_INT > 25) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }

            favoritePostsBinding.rootView.setBackgroundColor(getColor(R.color.light))

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.dark)
            window.navigationBarColor = getColor(R.color.dark)

            window.decorView.systemUiVisibility = 0

            favoritePostsBinding.rootView.setBackgroundColor(getColor(R.color.dark))

        }
    }

}