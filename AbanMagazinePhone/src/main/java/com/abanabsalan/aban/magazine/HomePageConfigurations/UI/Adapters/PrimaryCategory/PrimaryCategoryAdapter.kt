/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:40 AM
 * Last modified 4/25/22, 9:40 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.PrimaryCategory

import android.content.Intent
import android.text.Html
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesItemData
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.databinding.HomePagePrimaryCategoriesItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class PrimaryCategoryAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<PrimaryCategoryViewHolder>() {

    val categoriesItemData: ArrayList<CategoriesItemData> = ArrayList<CategoriesItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PrimaryCategoryViewHolder {

        return PrimaryCategoryViewHolder(HomePagePrimaryCategoriesItemBinding.inflate(context.layoutInflater, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return categoriesItemData.size
    }

    override fun onBindViewHolder(primaryCategoryViewHolder: PrimaryCategoryViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        primaryCategoryViewHolder.categoryNameView.text = Html.fromHtml(categoriesItemData[position].categoryName, Html.FROM_HTML_MODE_LEGACY)

        Glide.with(context)
            .asDrawable()
            .load("${CategoriesDataParameters.CategoryParameters.CategoryFeaturedImageBaseLink}${categoriesItemData[position].categoryId}")
            .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 19)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(primaryCategoryViewHolder.categoryFeaturedImage)

        primaryCategoryViewHolder.rootViewItem.setOnClickListener {

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