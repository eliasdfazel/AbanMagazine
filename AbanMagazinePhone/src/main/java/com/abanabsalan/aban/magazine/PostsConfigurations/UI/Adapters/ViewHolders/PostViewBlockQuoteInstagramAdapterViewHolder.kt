/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/19/20 5:40 PM
 * Last modified 7/19/20 5:40 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view_content_item_block_quote_instagram.view.*
import net.geekstools.imageview.customshapes.ShapesImage

class PostViewBlockQuoteInstagramAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val instagramPostImage: ShapesImage = view.instagramPostImage
    val instagramPostUsername: TextView = view.instagramPostUsername
    val instagramPostTitle: TextView = view.instagramPostTitle
}