/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 6:29 AM
 * Last modified 11/12/20 6:23 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.RecommendedPosts

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.text.Html
import android.view.LayoutInflater
import android.view.MotionEvent
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

class RecommendedPostsAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<RecommendedPostsViewHolder>() {

    val recommendedPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecommendedPostsViewHolder {

        return RecommendedPostsViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_newest_posts_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return recommendedPostsItemData.size
    }

    override fun onBindViewHolder(recommendedPostsViewHolder: RecommendedPostsViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(recommendedPostsViewHolder, position, dataPayloads)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                recommendedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                recommendedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                recommendedPostsViewHolder.postTitleView.setShadowLayer(recommendedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                recommendedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                recommendedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                recommendedPostsViewHolder.postTitleView.setShadowLayer(recommendedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

    }

    override fun onBindViewHolder(recommendedPostsViewHolder: RecommendedPostsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                recommendedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                recommendedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                recommendedPostsViewHolder.postTitleView.setShadowLayer(recommendedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val newestPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.newest_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                newestPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                recommendedPostsViewHolder.rootViewItem.background = newestPostsItemBackground

                recommendedPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                recommendedPostsViewHolder.postTitleView.setShadowLayer(recommendedPostsViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))

            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.red))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
            .asDrawable()
            .load(recommendedPostsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    context.runOnUiThread {
                        recommendedPostsViewHolder.postFeaturedImage.setImageDrawable(resource)
                    }

                    return false
                }

            })
            .submit()

        recommendedPostsViewHolder.postTitleView.text = Html.fromHtml(recommendedPostsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY)

        recommendedPostsViewHolder.rootViewItem.setOnClickListener {

            SinglePostView.show(
                context = context,
                featuredImageSharedElement = recommendedPostsViewHolder.postFeaturedImage as AppCompatImageView,
                postId = recommendedPostsItemData[position].postId,
                postFeaturedImage = recommendedPostsItemData[position].postFeaturedImage,
                postTitle = recommendedPostsItemData[position].postTitle,
                postContent = recommendedPostsItemData[position].postContent,
                postTags = recommendedPostsItemData[position].postTags,
                postExcerpt = recommendedPostsItemData[position].postExcerpt,
                postLink = recommendedPostsItemData[position].postLink,
                relatedPostStringJson = recommendedPostsItemData[position].relatedPostsContent
            )

        }

        recommendedPostsViewHolder.rootViewItem.setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {

                    recommendedPostsViewHolder.postFeaturedBlurryImage.setBlurRadius(0f)

                }
                MotionEvent.ACTION_UP -> {

                    recommendedPostsViewHolder.postFeaturedBlurryImage.setBlurRadius(13f)

                }
            }

            false
        }

    }

}