/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/14/20 10:45 AM
 * Last modified 7/14/20 10:37 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Extensions

import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts

fun AllCategoryPosts.setupUserInterface() {

    allCategoryPostsBinding.pageFluidSlider.startText = ""
    allCategoryPostsBinding.pageFluidSlider.endText = ""

    allCategoryPostsBinding.pageFluidSlider.positionListener = { sliderPosition ->
        allCategoryPostsBinding.pageFluidSlider.bubbleText = "${0 + (7  * sliderPosition).toInt()}"
    }

    allCategoryPostsBinding.pageFluidSlider.beginTrackingListener = { /* action on slider touched */ }

    allCategoryPostsBinding.pageFluidSlider.endTrackingListener = { /* action on slider released */ }

}