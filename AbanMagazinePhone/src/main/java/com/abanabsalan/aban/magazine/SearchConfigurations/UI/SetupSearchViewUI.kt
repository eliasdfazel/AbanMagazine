/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 2:01 AM
 * Last modified 8/10/20 2:01 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI

import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.hidePopupSearches
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.databinding.SearchPopupUiViewBinding

class SetupSearchViewUI (private val context: HomePage, private val searchPopupUiViewBinding: SearchPopupUiViewBinding) {

    init {

        val searchPopupUiViewParams = searchPopupUiViewBinding.textInputSearchView.layoutParams as ConstraintLayout.LayoutParams
        searchPopupUiViewParams.bottomMargin = searchPopupUiViewParams.bottomMargin + navigationBarHeight(context)
        searchPopupUiViewBinding.textInputSearchView.layoutParams = searchPopupUiViewParams

        searchPopupUiViewBinding.root.setOnClickListener {

            context.hidePopupSearches()

        }

    }

}