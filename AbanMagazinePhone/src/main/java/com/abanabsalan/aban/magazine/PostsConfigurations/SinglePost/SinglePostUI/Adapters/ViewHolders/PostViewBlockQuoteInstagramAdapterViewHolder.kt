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

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.PostViewContentItemBlockQuoteInstagramBinding
import net.geekstools.imageview.customshapes.ShapesImage

class PostViewBlockQuoteInstagramAdapterViewHolder (view: PostViewContentItemBlockQuoteInstagramBinding) : RecyclerView.ViewHolder(view.root) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val instagramPostImage: ShapesImage = view.instagramPostImage
    val instagramPostUsername: TextView = view.instagramPostUsername
    val instagramPostTitle: TextView = view.instagramPostTitle
}