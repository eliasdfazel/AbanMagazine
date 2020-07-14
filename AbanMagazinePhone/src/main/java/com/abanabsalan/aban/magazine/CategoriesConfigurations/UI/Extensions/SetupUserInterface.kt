/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/14/20 11:13 AM
 * Last modified 7/14/20 11:11 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Extensions

import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.Utils.UI.FluidSlider.FluidSliderAction
import com.abanabsalan.aban.magazine.Utils.UI.FluidSlider.FluidSliderActionInterface

fun AllCategoryPosts.setupUserInterface() {

  FluidSliderAction(object : FluidSliderActionInterface {

      override fun fluidSliderPosition(sliderPosition: Float) {
          super.fluidSliderPosition(sliderPosition)


      }

  }).startListener(allCategoryPostsBinding.pageFluidSlider)

}