/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/4/20 8:18 AM
 * Last modified 9/4/20 8:11 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.ProductShowcase

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.home_page_products_showcase_item.view.*
import net.geekstools.imageview.customshapes.ShapesImage

class ProductShowcaseViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val productImageView: ShapesImage = view.productImageView
    val productTitleView: TextView = view.productTitleView
    val productPurchaseView: MaterialButton = view.productPurchaseView
}