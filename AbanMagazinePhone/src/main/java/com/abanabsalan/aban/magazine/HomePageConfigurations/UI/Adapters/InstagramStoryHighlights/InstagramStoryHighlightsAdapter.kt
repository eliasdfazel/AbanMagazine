/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/8/20 8:32 AM
 * Last modified 8/8/20 8:32 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.InstagramStoryHighlights

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.DataHolder.StoryHighlightsItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class InstagramStoryHighlightsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<InstagramStoryHighlightsViewHolder>() {

    val storyHighlightsItemData: ArrayList<StoryHighlightsItemData> = ArrayList<StoryHighlightsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InstagramStoryHighlightsViewHolder {

        return InstagramStoryHighlightsViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_instagram_story_highlights_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return storyHighlightsItemData.size
    }

    override fun onBindViewHolder(instagramStoryHighlightsViewHolder: InstagramStoryHighlightsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        instagramStoryHighlightsViewHolder.storyHighlightsName.text = (storyHighlightsItemData[position].storyHighlightsName)

        val linkContent: Document = Jsoup.parse(storyHighlightsItemData[position].storyHighlightsName)

        instagramStoryHighlightsViewHolder.rootViewItem.setOnClickListener {

            BuiltInWebView.show(
                context = context,
                linkToLoad = storyHighlightsItemData[position].linkToStoryHighlight,
                gradientColorOne = context.getColor(R.color.instagramOne),
                gradientColorTwo = context.getColor(R.color.instagramThree)
            )

        }

        Glide.with(context)
            .asDrawable()
            .load("https://drive.google.com/uc?export=view&id=1sw3i4bDSyzFklKE-ZOljC8iRRNo51oVU")
            .transform(CenterCrop(), CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(instagramStoryHighlightsViewHolder.storyHighlightsCoverImage)

    }

}