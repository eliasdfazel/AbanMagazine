/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/13/20 1:51 PM
 * Last modified 7/13/20 1:29 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Extensions

import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts

fun AllCategoryPosts.setupUserInterface() {

    allCategoryPostsBinding.pageFluidSlider.outlineProvider = allCategoryPostsBinding.pageFluidSlider.OutlineProvider()

    allCategoryPostsBinding.pageFluidSlider.positionListener = { sliderPosition ->
        allCategoryPostsBinding.pageFluidSlider.bubbleText = "${0 + (7  * sliderPosition).toInt()}"
    }

    allCategoryPostsBinding.pageFluidSlider.beginTrackingListener = { /* action on slider touched */ }

    allCategoryPostsBinding.pageFluidSlider.endTrackingListener = { /* action on slider released */ }

}