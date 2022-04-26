/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/26/22, 7:31 AM
 * Last modified 4/26/22, 7:26 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Adapter

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.text.Html
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.Utils.SharePost
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractDominantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractVibrantColor
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.abanabsalan.aban.magazine.databinding.AllCategoryPostsItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class AllCategoryPostsAdapter (private val context: AllCategoryPosts, private val overallTheme: OverallTheme): RecyclerView.Adapter<AllCategoryPostsViewHolder>() {

    val postsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AllCategoryPostsViewHolder {

        return AllCategoryPostsViewHolder(AllCategoryPostsItemBinding.inflate(context.layoutInflater, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return postsItemData.size
    }

    override fun onBindViewHolder(allCategoryPostsViewHolder: AllCategoryPostsViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(allCategoryPostsViewHolder, position, dataPayloads)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                allCategoryPostsViewHolder.rootViewItem.background = categoryPostsItemBackground

                allCategoryPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))

                allCategoryPostsViewHolder.postExcerptView.setTextColor(context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                allCategoryPostsViewHolder.rootViewItem.background = categoryPostsItemBackground

                allCategoryPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))

                allCategoryPostsViewHolder.postExcerptView.setTextColor(context.getColor(R.color.light))

            }
        }

    }

    override fun onBindViewHolder(allCategoryPostsViewHolder: AllCategoryPostsViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.darker))

                allCategoryPostsViewHolder.rootViewItem.background = categoryPostsItemBackground

                allCategoryPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))

                allCategoryPostsViewHolder.postExcerptView.setTextColor(context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val categoryPostsItemBackground: LayerDrawable = context.getDrawable(R.drawable.category_posts_item_background) as LayerDrawable
                val temporaryForeground: Drawable = categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))
                categoryPostsItemBackground.findDrawableByLayerId(R.id.temporaryBackground).setTint(context.getColor(R.color.lighter))

                allCategoryPostsViewHolder.rootViewItem.background = categoryPostsItemBackground

                allCategoryPostsViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))

                allCategoryPostsViewHolder.postExcerptView.setTextColor(context.getColor(R.color.light))

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
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    context.runOnUiThread {
                        allCategoryPostsViewHolder.postFeatureImageView.setImageDrawable(resource)
                    }

                    return false
                }

            })
            .submit()

        allCategoryPostsViewHolder.postTitleView.text = Html.fromHtml(postsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY)
        allCategoryPostsViewHolder.postExcerptView.text = Html.fromHtml(postsItemData[position].postExcerpt, Html.FROM_HTML_MODE_LEGACY)

        Glide.with(context)
            .asGif()
            .load(R.raw.share_animation)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterInside(), RoundedCorners(23))
            .into(allCategoryPostsViewHolder.shareIcon)


        allCategoryPostsViewHolder.rootViewItem.setOnClickListener {

            Glide.with(context)
                .asDrawable()
                .load(postsItemData[position].postFeaturedImage)
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                        context.runOnUiThread {

                            BuiltInWebView.showPost(
                                context = context,
                                postId = postsItemData[position].postId,
                                postFeaturedImage = postsItemData[position].postFeaturedImage,
                                postTitle = postsItemData[position].postTitle,
                                postContent = postsItemData[position].postContent,
                                postTags = postsItemData[position].postTags,
                                postExcerpt = postsItemData[position].postExcerpt,
                                postLink = postsItemData[position].postLink,
                                relatedPostStringJson = postsItemData[position].relatedPostsContent,
                                gradientColorOne = extractDominantColor(context, resource?:context.getDrawable(R.drawable.official_business_logo)!!),
                                gradientColorTwo = extractVibrantColor(context, resource?:context.getDrawable(R.drawable.official_business_logo)!!),
                                linkToLoad = postsItemData[position].postLink,
                            )

                        }

                        return false
                    }

                })
                .submit()

        }

        allCategoryPostsViewHolder.shareIcon.setOnClickListener {

            SharePost(appCompatActivity = context)
                .invoke(
                    sharePostTitle = Html.fromHtml(postsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY).toString(),
                    sharePostExcerpt = Html.fromHtml(postsItemData[position].postExcerpt, Html.FROM_HTML_MODE_LEGACY).toString(),
                    sharePostLink = postsItemData[position].postLink
                )

        }

    }

}