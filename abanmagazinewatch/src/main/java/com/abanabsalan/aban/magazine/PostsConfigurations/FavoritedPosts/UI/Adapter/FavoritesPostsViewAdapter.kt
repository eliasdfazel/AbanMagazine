/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/5/20 4:45 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.Adapter

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.SharePost
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class FavoritesPostsViewAdapter (private val context: FavoritesPostsView, private val overallTheme: OverallTheme) : RecyclerView.Adapter<FavoritesPostsViewViewHolder>() {

    val postsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoritesPostsViewViewHolder {

        return FavoritesPostsViewViewHolder(LayoutInflater.from(context).inflate(R.layout.favorites_posts_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return postsItemData.size
    }

    override fun onBindViewHolder(favoritesPostsViewViewHolder: FavoritesPostsViewViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(favoritesPostsViewViewHolder, position, dataPayloads)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(
                    R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(
                    R.color.darker))

                favoritesPostsViewViewHolder.rootViewItem.background = categoryPostsItemBackground

                favoritesPostsViewViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))

                favoritesPostsViewViewHolder.postExcerptView.setTextColor(context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(
                    R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(
                    R.color.lighter))

                favoritesPostsViewViewHolder.rootViewItem.background = categoryPostsItemBackground

                favoritesPostsViewViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))

                favoritesPostsViewViewHolder.postExcerptView.setTextColor(context.getColor(R.color.light))

            }
        }

    }

    override fun onBindViewHolder(favoritesPostsViewViewHolder: FavoritesPostsViewViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(
                    R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(
                    R.color.darker))

                favoritesPostsViewViewHolder.rootViewItem.background = categoryPostsItemBackground

                favoritesPostsViewViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))

                favoritesPostsViewViewHolder.postExcerptView.setTextColor(context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(
                    R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(
                    R.color.lighter))

                favoritesPostsViewViewHolder.rootViewItem.background = categoryPostsItemBackground

                favoritesPostsViewViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))

                favoritesPostsViewViewHolder.postExcerptView.setTextColor(context.getColor(R.color.light))

            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.red))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
            .asDrawable()
            .load(postsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterCrop(), RoundedCorners(23))
            .into(favoritesPostsViewViewHolder.postFeatureImageView)

        favoritesPostsViewViewHolder.postTitleView.text = Html.fromHtml(postsItemData[position].postTitle)
        favoritesPostsViewViewHolder.postExcerptView.text = Html.fromHtml(postsItemData[position].postExcerpt)

        Glide.with(context)
            .asGif()
            .load(R.raw.share_animation)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterInside(), RoundedCorners(23))
            .into(favoritesPostsViewViewHolder.shareIcon)


        favoritesPostsViewViewHolder.rootViewItem.setOnClickListener {

            SinglePostView.show(
                context = context,
                featuredImageSharedElement = favoritesPostsViewViewHolder.postFeatureImageView as AppCompatImageView,
                postId = postsItemData[position].postId,
                postFeaturedImage = postsItemData[position].postFeaturedImage,
                postTitle = postsItemData[position].postTitle,
                postContent = postsItemData[position].postContent,
                postExcerpt = postsItemData[position].postExcerpt,
                postLink = postsItemData[position].postLink,
                relatedPostStringJson = postsItemData[position].relatedPostsContent

            )

        }

        favoritesPostsViewViewHolder.shareIcon.setOnClickListener {

            SharePost(appCompatActivity = context)
                .invoke(
                    sharePostTitle = Html.fromHtml(postsItemData[position].postTitle).toString(),
                    sharePostExcerpt = Html.fromHtml(postsItemData[position].postExcerpt).toString(),
                    sharePostLink = postsItemData[position].postLink
                )

        }

    }

}