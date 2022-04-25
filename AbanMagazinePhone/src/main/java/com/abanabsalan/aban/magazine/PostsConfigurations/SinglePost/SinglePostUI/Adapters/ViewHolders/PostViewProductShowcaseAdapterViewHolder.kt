/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.PostViewContentItemShowcaseVerticalBinding
import com.google.android.material.button.MaterialButton

class PostViewProductShowcaseAdapterViewHolder (view: PostViewContentItemShowcaseVerticalBinding) : RecyclerView.ViewHolder(view.root) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val purchaseButton: MaterialButton = view.purchaseButton
    val productInformation: ConstraintLayout = view.productInformation
    val productTitle: TextView = view.productTitle
    val productBrand: TextView = view.productBrand
    val productDescription: TextView = view.productDescription
    val productImage: ImageView = view.productImage
}