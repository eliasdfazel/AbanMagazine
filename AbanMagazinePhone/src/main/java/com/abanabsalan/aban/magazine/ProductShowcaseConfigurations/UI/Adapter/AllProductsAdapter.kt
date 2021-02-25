/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/25/21 8:29 AM
 * Last modified 2/25/21 8:29 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.Adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesItemData
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class AllProductsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<AllProductsViewHolder>() {

    val categoriesItemData: ArrayList<CategoriesItemData> = ArrayList<CategoriesItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AllProductsViewHolder {

        return AllProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_primary_categories_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return categoriesItemData.size
    }

    override fun onBindViewHolder(allProductsViewHolder: AllProductsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        allProductsViewHolder.productTitleView.text = Html.fromHtml(categoriesItemData[position].categoryName, Html.FROM_HTML_MODE_LEGACY)

        Glide.with(context)
            .asDrawable()
            .load("${CategoriesDataParameters.CategoryParameters.CategoryFeaturedImageBaseLink}${categoriesItemData[position].categoryId}")
            .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 19)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(allProductsViewHolder.productFeatureImageView)

        allProductsViewHolder.rootViewItem.setOnClickListener {



        }

    }

}