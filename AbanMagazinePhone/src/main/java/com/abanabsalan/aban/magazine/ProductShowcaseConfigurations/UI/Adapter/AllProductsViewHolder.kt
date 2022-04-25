/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:29 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.Adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.OnlineStoreAllProductsItemsBinding
import com.google.android.material.button.MaterialButton
import net.geekstools.imageview.customshapes.ShapesImage

class AllProductsViewHolder (view: OnlineStoreAllProductsItemsBinding) : RecyclerView.ViewHolder(view.root) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val productFeatureImageView: ShapesImage = view.productFeatureImageView
    val productTitleView: AppCompatTextView = view.productTitleView
    val productPriceView: MaterialButton = view.productPriceView
    val onSaleView: AppCompatImageView = view.onSaleView
    val addProductToBasket: MaterialButton = view.addProductToBasket
}