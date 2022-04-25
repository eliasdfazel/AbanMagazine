/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.RecommendedPosts

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.home_page_recommended_item.view.*
import net.geeksempire.blurry.effect.view.RealtimeBlurView

class RecommendedPostsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postFeaturedImage: AppCompatImageView = view.postFeaturedImage
    val postFeaturedBlurryImage: RealtimeBlurView = view.postFeaturedBlurryImage
    val postTitleView: TextView = view.postTitleView
    val readMoreView: MaterialButton = view.readMoreView
}