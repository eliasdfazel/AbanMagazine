/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:18 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.InstagramStoryHighlights

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.HomePageInstagramStoryHighlightsItemBinding
import net.geekstools.imageview.customshapes.ShapesImage

class InstagramStoryHighlightsViewHolder (homePageInstagramStoryHighlightsItemBinding: HomePageInstagramStoryHighlightsItemBinding) : RecyclerView.ViewHolder(homePageInstagramStoryHighlightsItemBinding.root) {
    val rootViewItem: ConstraintLayout = homePageInstagramStoryHighlightsItemBinding.rootViewItem
    val storyHighlightsCoverImage: ShapesImage = homePageInstagramStoryHighlightsItemBinding.storyHighlightsCoverImage
    val storyHighlightsName: TextView = homePageInstagramStoryHighlightsItemBinding.storyHighlightsName
}