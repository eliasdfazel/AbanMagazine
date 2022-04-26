/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/26/22, 7:31 AM
 * Last modified 4/26/22, 7:29 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.ProductShowcase

import android.content.res.ColorStateList
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.ProductJsonDataStructureItem
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.abanabsalan.aban.magazine.databinding.HomePageProductsShowcaseItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class HomeProductShowcaseAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<HomeProductShowcaseViewHolder>() {

    val productShowcaseItemData: ArrayList<ProductJsonDataStructureItem> = ArrayList<ProductJsonDataStructureItem>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeProductShowcaseViewHolder {

        return HomeProductShowcaseViewHolder(HomePageProductsShowcaseItemBinding.inflate(context.layoutInflater, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return productShowcaseItemData.size
    }

    override fun onBindViewHolder(homeProductShowcaseViewHolder: HomeProductShowcaseViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(homeProductShowcaseViewHolder, position, dataPayloads)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                homeProductShowcaseViewHolder.productTitleView.setTextColor(context.getColor(R.color.black))
                homeProductShowcaseViewHolder.productTitleView.setShadowLayer(homeProductShowcaseViewHolder.productTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

                homeProductShowcaseViewHolder.productPurchaseView.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.dark_transparent))
                homeProductShowcaseViewHolder.productPurchaseView.setTextColor(context.getColor(R.color.light))
                homeProductShowcaseViewHolder.productPurchaseView.iconTint = ColorStateList.valueOf(context.getColor(R.color.light))

            }
            ThemeType.ThemeDark -> {

                homeProductShowcaseViewHolder.productTitleView.setTextColor(context.getColor(R.color.white))
                homeProductShowcaseViewHolder.productTitleView.setShadowLayer(homeProductShowcaseViewHolder.productTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

                homeProductShowcaseViewHolder.productPurchaseView.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.light_transparent))
                homeProductShowcaseViewHolder.productPurchaseView.setTextColor(context.getColor(R.color.dark))
                homeProductShowcaseViewHolder.productPurchaseView.iconTint = ColorStateList.valueOf(context.getColor(R.color.dark))

            }
        }

    }

    override fun onBindViewHolder(homeProductShowcaseViewHolder: HomeProductShowcaseViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                homeProductShowcaseViewHolder.productTitleView.setTextColor(context.getColor(R.color.black))
                homeProductShowcaseViewHolder.productTitleView.setShadowLayer(homeProductShowcaseViewHolder.productTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

                homeProductShowcaseViewHolder.productPurchaseView.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.dark_transparent))
                homeProductShowcaseViewHolder.productPurchaseView.setTextColor(context.getColor(R.color.light))
                homeProductShowcaseViewHolder.productPurchaseView.iconTint = ColorStateList.valueOf(context.getColor(R.color.light))

            }
            ThemeType.ThemeDark -> {

                homeProductShowcaseViewHolder.productTitleView.setTextColor(context.getColor(R.color.white))
                homeProductShowcaseViewHolder.productTitleView.setShadowLayer(homeProductShowcaseViewHolder.productTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

                homeProductShowcaseViewHolder.productPurchaseView.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.light_transparent))
                homeProductShowcaseViewHolder.productPurchaseView.setTextColor(context.getColor(R.color.dark))
                homeProductShowcaseViewHolder.productPurchaseView.iconTint = ColorStateList.valueOf(context.getColor(R.color.dark))

            }
        }

        homeProductShowcaseViewHolder.productTitleView.text = (productShowcaseItemData[position].productName)

        homeProductShowcaseViewHolder.productPurchaseView.setOnClickListener {

            BuiltInWebView.showLink(
                context = context,
                linkToLoad = productShowcaseItemData[position].productLink,
                gradientColorOne = context.getColor(R.color.default_color_dark),
                gradientColorTwo = context.getColor(R.color.default_color_game_dark)
            )

        }

        Glide.with(context)
            .asDrawable()
            .load((productShowcaseItemData[position].productFeaturedImage))
            .transform(CenterCrop(), RoundedCorners(DpToInteger(context, 9)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(homeProductShowcaseViewHolder.productImageView)

    }

}