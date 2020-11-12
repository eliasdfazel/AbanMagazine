/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 6:19 AM
 * Last modified 11/12/20 6:14 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.RecommendedPosts

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_page_recommended_item.view.*
import net.geeksempire.blurry.effect.view.RealtimeBlurView
import net.geekstools.imageview.customshapes.ShapesImage

class RecommendedPostsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postFeaturedImage: ShapesImage = view.postFeaturedImage
    val postFeaturedBlurryImage: RealtimeBlurView = view.postFeaturedBlurryImage
    val postTitleView: TextView = view.postTitleView
}