/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.RelatedPosts.UI

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.RelatedPostsItemBinding
import net.geekstools.imageview.customshapes.ShapesImage

class RelatedPostsViewHolder (relatedPostsItemBinding: RelatedPostsItemBinding) : RecyclerView.ViewHolder(relatedPostsItemBinding.root) {
    val rootViewItem: ConstraintLayout = relatedPostsItemBinding.rootViewItem
    val postFeatureImageView: ShapesImage = relatedPostsItemBinding.postFeatureImageView
    val postTitleView: TextView = relatedPostsItemBinding.postTitleView
}