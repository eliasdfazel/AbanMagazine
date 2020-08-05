/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/5/20 3:46 AM
 * Last modified 8/5/20 3:46 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.favorites_posts_item.view.*
import net.geekstools.imageview.customshapes.ShapesImage

class FavoritesPostsViewViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postFeatureImageView: ShapesImage = view.postFeatureImageView
    val postTitleView: TextView = view.postTitleView
    val postExcerptView: TextView = view.postExcerptView
    val shareIcon: ImageView = view.shareIcon
}