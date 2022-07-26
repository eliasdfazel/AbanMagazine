/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/26/22, 7:31 AM
 * Last modified 4/26/22, 7:29 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.InstagramStoryHighlights

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.DataHolder.StoryHighlightsItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.abanabsalan.aban.magazine.databinding.HomePageInstagramStoryHighlightsItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class InstagramStoryHighlightsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<InstagramStoryHighlightsViewHolder>() {

    val storyHighlightsItemData: ArrayList<StoryHighlightsItemData> = ArrayList<StoryHighlightsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InstagramStoryHighlightsViewHolder {

        return InstagramStoryHighlightsViewHolder(HomePageInstagramStoryHighlightsItemBinding.inflate(context.layoutInflater, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return storyHighlightsItemData.size
    }

    override fun onBindViewHolder(instagramStoryHighlightsViewHolder: InstagramStoryHighlightsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                instagramStoryHighlightsViewHolder.storyHighlightsName.setTextColor(context.getColor(R.color.darker))
                instagramStoryHighlightsViewHolder.storyHighlightsName.setShadowLayer(instagramStoryHighlightsViewHolder.storyHighlightsName.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                instagramStoryHighlightsViewHolder.storyHighlightsName.setTextColor(context.getColor(R.color.lighter))
                instagramStoryHighlightsViewHolder.storyHighlightsName.setShadowLayer(instagramStoryHighlightsViewHolder.storyHighlightsName.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

        instagramStoryHighlightsViewHolder.storyHighlightsName.text = (storyHighlightsItemData[position].storyHighlightsName)

        instagramStoryHighlightsViewHolder.rootViewItem.setOnClickListener {

            BuiltInWebView.showLink(
                context = context,
                linkToLoad = storyHighlightsItemData[position].linkToStoryHighlight,
                gradientColorOne = context.getColor(R.color.instagramOne),
                gradientColorTwo = context.getColor(R.color.instagramThree)
            )

        }

        Glide.with(context)
            .asDrawable()
            .load(storyHighlightsItemData[position].storyHighlightsCoverImage)
            .transform(CenterCrop(), CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(instagramStoryHighlightsViewHolder.storyHighlightsCoverImage)

    }

}