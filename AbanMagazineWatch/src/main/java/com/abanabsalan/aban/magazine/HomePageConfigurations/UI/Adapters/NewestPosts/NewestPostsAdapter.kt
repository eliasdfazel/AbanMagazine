/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 2:15 AM
 * Last modified 8/5/20 4:45 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.NewestPosts

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
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

class NewestPostsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<NewestPostsViewHolder>() {

    val newestPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewestPostsViewHolder {

        return NewestPostsViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_newest_posts_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return newestPostsItemData.size
    }

    override fun onBindViewHolder(newestPostsViewHolder: NewestPostsViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(newestPostsViewHolder, position, dataPayloads)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                newestPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                newestPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                newestPostsViewHolder.postTitleView.setShadowLayer(newestPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                newestPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                newestPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                newestPostsViewHolder.postTitleView.setShadowLayer(newestPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

    }

    override fun onBindViewHolder(newestPostsViewHolder: NewestPostsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                newestPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                newestPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                newestPostsViewHolder.postTitleView.setShadowLayer(newestPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                newestPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                newestPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                newestPostsViewHolder.postTitleView.setShadowLayer(newestPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.red))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
            .asDrawable()
            .load(newestPostsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    context.runOnUiThread {
                        newestPostsViewHolder.postFeatureImageView.setImageDrawable(resource)
                    }

                    return false
                }

            })
            .submit()

        newestPostsViewHolder.postTitleView.text = Html.fromHtml(newestPostsItemData[position].postTitle)

        newestPostsViewHolder.rootViewItem.setOnClickListener {

            SinglePostView.show(
                context = context,
                featuredImageSharedElement = newestPostsViewHolder.postFeatureImageView as AppCompatImageView,
                postId = newestPostsItemData[position].postId,
                postFeaturedImage = newestPostsItemData[position].postFeaturedImage,
                postTitle = newestPostsItemData[position].postTitle,
                postContent = newestPostsItemData[position].postContent,
                postExcerpt = newestPostsItemData[position].postExcerpt,
                postLink = newestPostsItemData[position].postLink,
                relatedPostStringJson = newestPostsItemData[position].relatedPostsContent
            )

        }

    }

}