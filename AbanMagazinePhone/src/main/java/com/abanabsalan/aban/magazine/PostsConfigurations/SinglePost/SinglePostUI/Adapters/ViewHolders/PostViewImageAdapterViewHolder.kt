/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/31/20 10:34 AM
 * Last modified 12/31/20 10:34 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view_content_item_image.view.*
import net.geeksempire.loadingspin.SpinKitView
import net.geekstools.imageview.customshapes.ShapesImage

class PostViewImageAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postImage: ShapesImage = view.postImage
    val postImageDescription: TextView = view.postImageDescription
    val postImageLoading: SpinKitView = view.postImageLoading
}