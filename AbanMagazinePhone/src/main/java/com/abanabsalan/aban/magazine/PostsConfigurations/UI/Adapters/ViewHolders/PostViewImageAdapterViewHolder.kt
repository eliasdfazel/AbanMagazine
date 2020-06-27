/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/26/20 7:05 PM
 * Last modified 6/26/20 4:35 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view_content_item_image.view.*
import net.geeksempire.loadingspin.SpinKitView

class PostViewImageAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postImage: ImageView = view.postImage
    val postImageLoading: SpinKitView = view.postImageLoading
}