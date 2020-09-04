/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/4/20 8:48 AM
 * Last modified 9/4/20 8:47 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.ProductShowcase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.ProductShowcaseItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class ProductShowcaseAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<ProductShowcaseViewHolder>() {

    val productShowcaseItemData: ArrayList<ProductShowcaseItemData> = ArrayList<ProductShowcaseItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductShowcaseViewHolder {

        return ProductShowcaseViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_products_showcase_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return productShowcaseItemData.size
    }

    override fun onBindViewHolder(productShowcaseViewHolder: ProductShowcaseViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                productShowcaseViewHolder.productTitleView.setTextColor(context.getColor(R.color.darker))
                productShowcaseViewHolder.productTitleView.setShadowLayer(productShowcaseViewHolder.productTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                productShowcaseViewHolder.productTitleView.setTextColor(context.getColor(R.color.lighter))
                productShowcaseViewHolder.productTitleView.setShadowLayer(productShowcaseViewHolder.productTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

        productShowcaseViewHolder.productTitleView.text = (productShowcaseItemData[position].nameOfProduct)

        productShowcaseViewHolder.productPurchaseView.setOnClickListener {

            BuiltInWebView.show(
                context = context,
                linkToLoad = productShowcaseItemData[position].linkToProduct,
                gradientColorOne = context.getColor(R.color.instagramOne),
                gradientColorTwo = context.getColor(R.color.instagramThree)
            )

        }

        Glide.with(context)
            .asDrawable()
            .load(productShowcaseItemData[position].linkToImageProduct)
            .transform(CenterCrop(), CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(productShowcaseViewHolder.productImageView)

    }

}