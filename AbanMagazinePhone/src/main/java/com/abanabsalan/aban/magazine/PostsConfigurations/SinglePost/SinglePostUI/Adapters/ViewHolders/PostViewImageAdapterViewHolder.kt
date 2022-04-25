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
import com.abanabsalan.aban.magazine.databinding.PostViewContentItemImageBinding
import net.geeksempire.loadingspin.SpinKitView
import net.geekstools.imageview.customshapes.ShapesImage

class PostViewImageAdapterViewHolder (view: PostViewContentItemImageBinding) : RecyclerView.ViewHolder(view.root) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postImage: ShapesImage = view.postImage
    val postImageDescription: TextView = view.postImageDescription
    val postImageLoading: SpinKitView = view.postImageLoading
}