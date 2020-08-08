/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/8/20 7:32 AM
 * Last modified 8/8/20 7:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.InstagramStoryHighlights

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.DataHolder.StoryHighlightsItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType

class InstagramStoryHighlightsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<InstagramStoryHighlightsViewHolder>() {

    val storyHighlightsItemData: ArrayList<StoryHighlightsItemData> = ArrayList<StoryHighlightsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InstagramStoryHighlightsViewHolder {

        return InstagramStoryHighlightsViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.home_page_instagram_story_highlights_item, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {

        return storyHighlightsItemData.size
    }

    override fun onBindViewHolder(instagramStoryHighlightsViewHolder: InstagramStoryHighlightsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

//        instagramStoryHighlightsViewHolder.categoryNameView.text = Html.fromHtml(categoriesItemData[position].categoryName)
//
//        Glide.with(context)
//            .asDrawable()
//            .load("${CategoriesDataParameters.CategoryParameters.CategoryFeaturedImageBaseLink}${categoriesItemData[position].categoryId}")
//            .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 13)))
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(instagramStoryHighlightsViewHolder.categoryFeaturedImage)
//
//        instagramStoryHighlightsViewHolder.rootViewItem.setOnClickListener {
//
//            Intent(context, AllCategoryPosts::class.java).apply {
//                putExtra(CategoriesDataParameters.CategoryParameters.CategoryLink, categoriesItemData[position].categoryLink)
//                putExtra(CategoriesDataParameters.CategoryParameters.CategoryId, categoriesItemData[position].categoryId)
//                putExtra(CategoriesDataParameters.CategoryParameters.CategoryName, categoriesItemData[position].categoryName)
//                putExtra(CategoriesDataParameters.CategoryParameters.CategoryDescription, categoriesItemData[position].categoryDescription)
//                context.startActivity(this@apply)
//            }
//
//        }

    }

}