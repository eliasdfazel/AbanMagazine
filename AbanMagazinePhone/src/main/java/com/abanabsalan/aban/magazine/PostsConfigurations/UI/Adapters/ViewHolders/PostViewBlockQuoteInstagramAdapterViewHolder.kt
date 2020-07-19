/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/19/20 2:34 PM
 * Last modified 7/19/20 1:38 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view_content_item_block_quote_instagram.view.*
import net.geeksempire.loadingspin.SpinKitView
import net.geekstools.imageview.customshapes.ShapesImage

class PostViewBlockQuoteInstagramAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postImage: ShapesImage = view.postImage
    val postImageLoading: SpinKitView = view.postImageLoading
}