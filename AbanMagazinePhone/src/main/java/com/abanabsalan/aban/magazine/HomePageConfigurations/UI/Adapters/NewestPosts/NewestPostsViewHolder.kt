/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.NewestPosts

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.HomePageNewestPostsItemBinding
import net.geekstools.imageview.customshapes.ShapesImage

class NewestPostsViewHolder (homePageNewestPostsItemBinding: HomePageNewestPostsItemBinding) : RecyclerView.ViewHolder(homePageNewestPostsItemBinding.root) {
    val rootViewItem: ConstraintLayout = homePageNewestPostsItemBinding.rootViewItem
    val postFeatureImageView: ShapesImage = homePageNewestPostsItemBinding.postFeatureImageView
    val postTitleView: TextView = homePageNewestPostsItemBinding.postTitleView
}