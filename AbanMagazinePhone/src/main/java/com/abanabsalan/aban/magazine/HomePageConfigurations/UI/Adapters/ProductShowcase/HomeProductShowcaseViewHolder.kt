/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/24/21 7:56 AM
 * Last modified 2/24/21 7:05 AM
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

class HomeProductShowcaseViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val productImageView: ShapesImage = view.productImageView
    val productTitleView: TextView = view.productTitleView
    val productPurchaseView: MaterialButton = view.productPurchaseView
}