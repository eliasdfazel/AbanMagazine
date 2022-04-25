/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.Adapter

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
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

                val productsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = productsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.lighter))
                productsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                allProductsViewHolder.rootViewItem.background = productsItemBackground

                allProductsViewHolder.productTitleView.setTextColor(context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val productsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = productsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.darker))
                productsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                allProductsViewHolder.rootViewItem.background = productsItemBackground

                allProductsViewHolder.productTitleView.setTextColor(context.getColor(R.color.light))

            }
        }

        allProductsViewHolder.onSaleView.visibility = if (productJsonDataStructureItem[position].productOnSale) {

            allProductsViewHolder.productPriceView.text = Html.fromHtml(
                "<strike>" + "$" + productJsonDataStructureItem[position].productPrice + "</strike>" +
                        "<br/>" + "<b>" + "$" + productJsonDataStructureItem[position].productSalePrice + "</b>",
                Html.FROM_HTML_MODE_LEGACY)

            View.VISIBLE
        } else {

            allProductsViewHolder.productPriceView.text = Html.fromHtml("<b>" + "$" + productJsonDataStructureItem[position].productPrice + "</b>", Html.FROM_HTML_MODE_LEGACY)

            View.GONE
        }

        allProductsViewHolder.productTitleView.text = Html.fromHtml(productJsonDataStructureItem[position].productName, Html.FROM_HTML_MODE_LEGACY)

        Glide.with(context)
            .asDrawable()
            .load(productJsonDataStructureItem[position].productFeaturedImage)
            .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 7)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(allProductsViewHolder.productFeatureImageView)

        allProductsViewHolder.rootViewItem.setOnClickListener {

            context.startActivity(Intent().apply {
                data = Uri.parse(productJsonDataStructureItem[position].productLink)
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })

        }

        allProductsViewHolder.addProductToBasket.setOnClickListener {

            context.startActivity(Intent().apply {
                data = Uri.parse(productJsonDataStructureItem[position].productLink)
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })

        }

        allProductsViewHolder.productPriceView.setOnClickListener {



        }

    }

}