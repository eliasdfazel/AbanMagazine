/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 3:11 AM
 * Last modified 8/13/20 2:52 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.InstagramStoryHighlights

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_page_instagram_story_highlights_item_watch.view.*
import net.geekstools.imageview.customshapes.ShapesImage

class InstagramStoryHighlightsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val storyHighlightsCoverImage: ShapesImage = view.storyHighlightsCoverImage
    val storyHighlightsName: TextView = view.storyHighlightsName
}