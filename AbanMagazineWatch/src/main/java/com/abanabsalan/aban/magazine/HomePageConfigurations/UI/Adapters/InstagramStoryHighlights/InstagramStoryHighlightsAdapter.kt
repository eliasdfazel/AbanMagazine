/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 7:12 AM
 * Last modified 8/13/20 7:05 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.InstagramStoryHighlights

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.ConfirmationOverlay
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.DataHolder.StoryHighlightsItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.wearable.intent.RemoteIntent
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class InstagramStoryHighlightsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<InstagramStoryHighlightsViewHolder>() {

    val storyHighlightsItemData: ArrayList<StoryHighlightsItemData> = ArrayList<StoryHighlightsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InstagramStoryHighlightsViewHolder {

        return InstagramStoryHighlightsViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_instagram_story_highlights_item_watch, viewGroup, false))
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

        val linkContent: Document = Jsoup.parse(storyHighlightsItemData[position].storyHighlightsName)

        instagramStoryHighlightsViewHolder.rootViewItem.setOnClickListener {

            val resultReceiver = object : ResultReceiver(Handler()) {

                override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {

                    if (resultCode == RemoteIntent.RESULT_OK) {

                        ConfirmationOverlay()
                            .setMessage(context.getString(R.string.checkYourPhone))
                            .setDuration(1000 * 1)
                            .showOn(context)

                    } else if (resultCode == RemoteIntent.RESULT_FAILED) {

                    } else {

                    }
                }
            }

            val intentPlayStore = Intent(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setData(Uri.parse(storyHighlightsItemData[position].linkToStoryHighlight))

            RemoteIntent.startRemoteActivity(
                context,
                intentPlayStore,
                resultReceiver)

        }

        Glide.with(context)
            .asDrawable()
            .load(storyHighlightsItemData[position].storyHighlightsCoverImage)
            .transform(CenterCrop(), CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(instagramStoryHighlightsViewHolder.storyHighlightsCoverImage)

    }

}