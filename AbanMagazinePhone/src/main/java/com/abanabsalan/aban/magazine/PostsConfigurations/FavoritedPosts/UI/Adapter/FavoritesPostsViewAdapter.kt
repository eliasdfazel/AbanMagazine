/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:40 AM
 * Last modified 4/25/22, 9:40 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.Adapter

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.text.Html
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.SharePost
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.abanabsalan.aban.magazine.databinding.FavoritesPostsItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class FavoritesPostsViewAdapter (private val context: FavoritesPostsView, private val overallTheme: OverallTheme) : RecyclerView.Adapter<FavoritesPostsViewViewHolder>() {

    val postsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoritesPostsViewViewHolder {

        return FavoritesPostsViewViewHolder(FavoritesPostsItemBinding.inflate(context.layoutInflater, viewGroup, false))
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

        favoritesPostsViewViewHolder.postTitleView.text = Html.fromHtml(postsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY)
        favoritesPostsViewViewHolder.postExcerptView.text = Html.fromHtml(postsItemData[position].postExcerpt, Html.FROM_HTML_MODE_LEGACY)

        Glide.with(context)
            .asGif()
            .load(R.raw.share_animation)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterInside(), RoundedCorners(23))
            .into(favoritesPostsViewViewHolder.shareIcon)


        favoritesPostsViewViewHolder.rootViewItem.setOnClickListener {

//            SinglePostView.show(
//                context = context,
//                featuredImageSharedElement = favoritesPostsViewViewHolder.postFeatureImageView as AppCompatImageView,
//                postId = postsItemData[position].postId,
//                postFeaturedImage = postsItemData[position].postFeaturedImage,
//                postTitle = postsItemData[position].postTitle,
//                postContent = postsItemData[position].postContent,
//                postTags = postsItemData[position].postTags,
//                postExcerpt = postsItemData[position].postExcerpt,
//                postLink = postsItemData[position].postLink,
//                relatedPostStringJson = postsItemData[position].relatedPostsContent
//            )

            BuiltInWebView.show(
                context = context,
                linkToLoad = postsItemData[position].postLink,
                gradientColorOne = context.getColor(R.color.instagramOne),
                gradientColorTwo = context.getColor(R.color.instagramThree)
            )

        }

        favoritesPostsViewViewHolder.shareIcon.setOnClickListener {

            SharePost(appCompatActivity = context)
                .invoke(
                    sharePostTitle = Html.fromHtml(postsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY).toString(),
                    sharePostExcerpt = Html.fromHtml(postsItemData[position].postExcerpt, Html.FROM_HTML_MODE_LEGACY).toString(),
                    sharePostLink = postsItemData[position].postLink
                )

        }

    }

}