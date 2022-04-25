/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 5:58 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Extensions

import android.text.Html
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType

fun AllCategoryPosts.setupUserInterface(categoryName: String, categoryDescription: String) {

    allCategoryPostsBinding.toolbarTitle.text = Html.fromHtml(categoryName, Html.FROM_HTML_MODE_LEGACY)
    allCategoryPostsBinding.toolbarDescription.setAnimatedInputText(Html.fromHtml(categoryDescription, Html.FROM_HTML_MODE_LEGACY))

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.navigationBarColor = getColor(R.color.light)

            allCategoryPostsBinding.rootView.setBackgroundColor(getColor(R.color.light))

        }
        ThemeType.ThemeDark -> {

            window.navigationBarColor = getColor(R.color.dark)

            allCategoryPostsBinding.rootView.setBackgroundColor(getColor(R.color.dark))

        }
    }

}