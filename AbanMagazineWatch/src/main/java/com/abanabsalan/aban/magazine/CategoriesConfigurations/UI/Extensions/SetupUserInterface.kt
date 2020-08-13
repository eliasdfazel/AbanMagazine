/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
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

    allCategoryPostsBinding.toolbarTitle.text = Html.fromHtml(categoryName)
    allCategoryPostsBinding.toolbarDescription.setAnimatedInputText(Html.fromHtml(categoryDescription))

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