/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/20/20 8:15 PM
 * Last modified 7/20/20 7:16 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SecondaryCategory

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesItemData
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class SecondaryCategoryAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<SecondaryCategoryViewHolder>() {

    val categoriesItemData: ArrayList<CategoriesItemData> = ArrayList<CategoriesItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SecondaryCategoryViewHolder {

        return SecondaryCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_secondary_categories_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return categoriesItemData.size
    }

    override fun onBindViewHolder(secondaryCategoryViewHolder: SecondaryCategoryViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        secondaryCategoryViewHolder.categoryNameView.text = Html.fromHtml(categoriesItemData[position].categoryName)

        Glide.with(context)
            .asDrawable()
            .load("${CategoriesDataParameters.CategoryParameters.CategoryFeaturedImageBaseLink}${categoriesItemData[position].categoryId}")
            .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 13)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(secondaryCategoryViewHolder.categoryFeaturedImage)

        secondaryCategoryViewHolder.rootViewItem.setOnClickListener {

            Intent(context, AllCategoryPosts::class.java).apply {
                putExtra(CategoriesDataParameters.CategoryParameters.CategoryLink, categoriesItemData[position].categoryLink)
                putExtra(CategoriesDataParameters.CategoryParameters.CategoryId, categoriesItemData[position].categoryId)
                putExtra(CategoriesDataParameters.CategoryParameters.CategoryName, categoriesItemData[position].categoryName)
                putExtra(CategoriesDataParameters.CategoryParameters.CategoryDescription, categoriesItemData[position].categoryDescription)
                context.startActivity(this@apply)
            }

        }

    }

}