/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.RelatedPosts.UI

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class RelatedPostsAdapter (private val context: SinglePostView, private val overallTheme: OverallTheme): RecyclerView.Adapter<RelatedPostsViewHolder>() {

    val relatedPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RelatedPostsViewHolder {

        return RelatedPostsViewHolder(LayoutInflater.from(context).inflate(R.layout.related_posts_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return relatedPostsItemData.size
    }

    override fun onBindViewHolder(relatedPostsViewHolder: RelatedPostsViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(relatedPostsViewHolder, position, dataPayloads)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                relatedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                relatedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                relatedPostsViewHolder.postTitleView.setShadowLayer(relatedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                relatedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                relatedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                relatedPostsViewHolder.postTitleView.setShadowLayer(relatedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

    }

    override fun onBindViewHolder(relatedPostsViewHolder: RelatedPostsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                relatedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                relatedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                relatedPostsViewHolder.postTitleView.setShadowLayer(relatedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                relatedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                relatedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                relatedPostsViewHolder.postTitleView.setShadowLayer(relatedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.red))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
            .asDrawable()
            .load(relatedPostsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    context.runOnUiThread {
                        relatedPostsViewHolder.postFeatureImageView.setImageDrawable(resource)
                    }

                    return false
                }

            })
            .submit()

        relatedPostsViewHolder.postTitleView.text = Html.fromHtml(relatedPostsItemData[position].postTitle)

        relatedPostsViewHolder.rootViewItem.setOnClickListener {

            SinglePostView.show(
                context = context,
                featuredImageSharedElement = relatedPostsViewHolder.postFeatureImageView as AppCompatImageView,
                postId = relatedPostsItemData[position].postId,
                postFeaturedImage = relatedPostsItemData[position].postFeaturedImage,
                postTitle = relatedPostsItemData[position].postTitle,
                postContent = relatedPostsItemData[position].postContent,
                postExcerpt = relatedPostsItemData[position].postExcerpt,
                postLink = relatedPostsItemData[position].postLink,
                relatedPostStringJson = relatedPostsItemData[position].relatedPostsContent
            )

        }

    }

}