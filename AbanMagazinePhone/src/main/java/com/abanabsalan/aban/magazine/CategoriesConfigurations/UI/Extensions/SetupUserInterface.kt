/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/14/20 12:40 PM
 * Last modified 7/14/20 12:27 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Extensions

import android.text.Html
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts

fun AllCategoryPosts.setupUserInterface(categoryName: String, categoryDescription: String) {

    allCategoryPostsBinding.toolbarTitle.text = Html.fromHtml(categoryName)
    allCategoryPostsBinding.toolbarDescription.setAnimatedInputText(Html.fromHtml(categoryDescription))

}