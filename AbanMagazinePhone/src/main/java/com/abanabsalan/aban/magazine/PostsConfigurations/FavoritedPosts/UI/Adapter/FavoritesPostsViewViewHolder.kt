/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.Adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.FavoritesPostsItemBinding
import net.geekstools.imageview.customshapes.ShapesImage

class FavoritesPostsViewViewHolder (favoritesPostsItemBinding: FavoritesPostsItemBinding) : RecyclerView.ViewHolder(favoritesPostsItemBinding.root) {
    val rootViewItem: ConstraintLayout = favoritesPostsItemBinding.rootViewItem
    val postFeatureImageView: ShapesImage = favoritesPostsItemBinding.postFeatureImageView
    val postTitleView: TextView = favoritesPostsItemBinding.postTitleView
    val postExcerptView: TextView = favoritesPostsItemBinding.postExcerptView
    val shareIcon: ImageView = favoritesPostsItemBinding.shareIcon
}