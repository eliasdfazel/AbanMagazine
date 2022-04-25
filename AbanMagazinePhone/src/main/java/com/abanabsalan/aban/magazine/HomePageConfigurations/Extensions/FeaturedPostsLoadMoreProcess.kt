/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
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

                    if (moreFeaturedPostAvailable) {

                        startFeaturedPostCategoryRetrieval(applicationContext, homePageViewBinding, homePageLiveData, PageCounter.PageNumberToLoad)

                    }

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