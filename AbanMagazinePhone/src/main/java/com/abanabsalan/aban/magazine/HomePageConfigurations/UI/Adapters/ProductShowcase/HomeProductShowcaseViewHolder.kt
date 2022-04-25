/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.ProductShowcase

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.HomePageProductsShowcaseItemBinding
import com.google.android.material.button.MaterialButton
import net.geekstools.imageview.customshapes.ShapesImage

class HomeProductShowcaseViewHolder (homePageProductsShowcaseItemBinding: HomePageProductsShowcaseItemBinding) : RecyclerView.ViewHolder(homePageProductsShowcaseItemBinding.root) {
    val rootViewItem: ConstraintLayout = homePageProductsShowcaseItemBinding.rootViewItem
    val productImageView: ShapesImage = homePageProductsShowcaseItemBinding.productImageView
    val productTitleView: TextView = homePageProductsShowcaseItemBinding.productTitleView
    val productPurchaseView: MaterialButton = homePageProductsShowcaseItemBinding.productPurchaseView
}