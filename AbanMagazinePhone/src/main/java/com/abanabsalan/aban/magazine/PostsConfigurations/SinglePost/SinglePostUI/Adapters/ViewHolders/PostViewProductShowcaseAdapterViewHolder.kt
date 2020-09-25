/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/25/20 11:27 AM
 * Last modified 9/25/20 10:34 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.post_view_content_item_showcase.view.*

class PostViewProductShowcaseAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val purchaseButton: MaterialButton = view.purchaseButton
    val productTitle: TextView = view.productTitle
    val productBrand: TextView = view.productBrand
    val productDescription: TextView = view.productDescription
    val productImage: ImageView = view.productImage
}