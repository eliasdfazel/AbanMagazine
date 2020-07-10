/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/10/20 12:53 PM
 * Last modified 7/10/20 12:21 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder.HomePageLiveData
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory.SpecificCategoryAdapter
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Utils.PageCounter

fun HomePage.startFeaturedPostsLoadMoreListener(homePageLiveData: HomePageLiveData, specificCategoryAdapter: SpecificCategoryAdapter) {

    homePageViewBinding.featuredPostsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            when(newState) {
                RecyclerView.SCROLL_STATE_SETTLING -> {

                    PageCounter.PageNumberToLoad = PageCounter.PageNumberToLoad.plus(1)

                    startSpecificCategoryRetrieval(applicationContext, homePageLiveData, PageCounter.PageNumberToLoad)

                }
                RecyclerView.SCROLL_STATE_IDLE -> {

                }
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }

    })

}