/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/25/21 11:12 AM
 * Last modified 2/25/21 11:12 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.Adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.ProductJsonDataStructureItem
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.OnlineStore
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class AllProductsAdapter (private val context: OnlineStore, private val overallTheme: OverallTheme): RecyclerView.Adapter<AllProductsViewHolder>() {

    val productJsonDataStructureItem: ArrayList<ProductJsonDataStructureItem> = ArrayList<ProductJsonDataStructureItem>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AllProductsViewHolder {

        return AllProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.online_store_all_products_items, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return productJsonDataStructureItem.size
    }

    override fun onBindViewHolder(allProductsViewHolder: AllProductsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        allProductsViewHolder.productTitleView.text = Html.fromHtml(productJsonDataStructureItem[position].productName, Html.FROM_HTML_MODE_LEGACY)

        Glide.with(context)
            .asDrawable()
            .load(productJsonDataStructureItem[position].productFeaturedImage)
            .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 19)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(allProductsViewHolder.productFeatureImageView)

        allProductsViewHolder.rootViewItem.setOnClickListener {



        }

    }

}