/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.RecommendedPosts

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.HomePageRecommendedItemBinding
import com.google.android.material.button.MaterialButton
import net.geeksempire.blurry.effect.view.RealtimeBlurView

class RecommendedPostsViewHolder (homePageRecommendedItemBinding: HomePageRecommendedItemBinding) : RecyclerView.ViewHolder(homePageRecommendedItemBinding.root) {
    val rootViewItem: ConstraintLayout = homePageRecommendedItemBinding.rootViewItem
    val postFeaturedImage: AppCompatImageView = homePageRecommendedItemBinding.postFeaturedImage
    val postFeaturedBlurryImage: RealtimeBlurView = homePageRecommendedItemBinding.postFeaturedBlurryImage
    val postTitleView: TextView = homePageRecommendedItemBinding.postTitleView
    val readMoreView: MaterialButton = homePageRecommendedItemBinding.readMoreView
}