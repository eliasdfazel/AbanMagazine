/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:40 AM
 * Last modified 4/25/22, 9:40 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.text.Html
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.OverallTheme
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import com.abanabsalan.aban.magazine.databinding.HomePageSpecificCategoryItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class SpecificCategoryAdapter (private val context: HomePage, private val overallTheme: OverallTheme): RecyclerView.Adapter<SpecificCategoryViewHolder>() {

    val specificCategoryPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SpecificCategoryViewHolder {

        return SpecificCategoryViewHolder(HomePageSpecificCategoryItemBinding.inflate(context.layoutInflater, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return specificCategoryPostsItemData.size
    }

    override fun onBindViewHolder(specificCategoryViewHolder: SpecificCategoryViewHolder, position: Int, dataPayloads: MutableList<Any>) {
        super.onBindViewHolder(specificCategoryViewHolder, position, dataPayloads)

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val specificCategoryItemBackground: LayerDrawable = context.getDrawable(R.drawable.specific_category_item_background) as LayerDrawable
                val temporaryForeground: Drawable = specificCategoryItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))

                specificCategoryViewHolder.rootViewItem.background = specificCategoryItemBackground

                specificCategoryViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                specificCategoryViewHolder.postTitleView.setShadowLayer(specificCategoryViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))
                specificCategoryViewHolder.postExcerptView.setTextColor(context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val specificCategoryItemBackground: LayerDrawable = context.getDrawable(R.drawable.specific_category_item_background) as LayerDrawable
                val temporaryForeground: Drawable = specificCategoryItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))

                specificCategoryViewHolder.rootViewItem.background = specificCategoryItemBackground

                specificCategoryViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                specificCategoryViewHolder.postTitleView.setShadowLayer(specificCategoryViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))
                specificCategoryViewHolder.postExcerptView.setTextColor(context.getColor(R.color.light))

            }
        }

    }

    override fun onBindViewHolder(specificCategoryViewHolder: SpecificCategoryViewHolder, position: Int) {

        when (overallTheme.checkThemeLightDark()) {
            ThemeType.ThemeLight -> {

                val specificCategoryItemBackground: LayerDrawable = context.getDrawable(R.drawable.specific_category_item_background) as LayerDrawable
                val temporaryForeground: Drawable = specificCategoryItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.light))

                specificCategoryViewHolder.rootViewItem.background = specificCategoryItemBackground

                specificCategoryViewHolder.postTitleView.setTextColor(context.getColor(R.color.darker))
                specificCategoryViewHolder.postTitleView.setShadowLayer(specificCategoryViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.dark))
                specificCategoryViewHolder.postExcerptView.setTextColor(context.getColor(R.color.dark))

            }
            ThemeType.ThemeDark -> {

                val specificCategoryItemBackground: LayerDrawable = context.getDrawable(R.drawable.specific_category_item_background) as LayerDrawable
                val temporaryForeground: Drawable = specificCategoryItemBackground.findDrawableByLayerId(R.id.temporaryForeground)
                temporaryForeground.setTint(context.getColor(R.color.dark))

                specificCategoryViewHolder.rootViewItem.background = specificCategoryItemBackground

                specificCategoryViewHolder.postTitleView.setTextColor(context.getColor(R.color.lighter))
                specificCategoryViewHolder.postTitleView.setShadowLayer(specificCategoryViewHolder.postTitleView.shadowRadius,0f,0f,context.getColor(R.color.light))
                specificCategoryViewHolder.postExcerptView.setTextColor(context.getColor(R.color.light))

            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.red))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
            .asDrawable()
            .load(specificCategoryPostsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    context.runOnUiThread {
                        specificCategoryViewHolder.postFeatureImageView.setImageDrawable(resource)
                    }

                    return false
                }

            })
            .submit()

        specificCategoryViewHolder.postTitleView.text = Html.fromHtml(specificCategoryPostsItemData[position].postTitle, Html.FROM_HTML_MODE_LEGACY)
        specificCategoryViewHolder.postExcerptView.text = Html.fromHtml(specificCategoryPostsItemData[position].postExcerpt, Html.FROM_HTML_MODE_LEGACY)

        specificCategoryViewHolder.rootViewItem.setOnClickListener {

//            SinglePostView.show(
//                context = context,
//                featuredImageSharedElement = specificCategoryViewHolder.postFeatureImageView as AppCompatImageView,
//                postId = specificCategoryPostsItemData[position].postId,
//                postFeaturedImage = specificCategoryPostsItemData[position].postFeaturedImage,
//                postTitle = specificCategoryPostsItemData[position].postTitle,
//                postContent = specificCategoryPostsItemData[position].postContent,
//                postTags = specificCategoryPostsItemData[position].postTags,
//                postExcerpt = specificCategoryPostsItemData[position].postExcerpt,
//                postLink = specificCategoryPostsItemData[position].postLink,
//                relatedPostStringJson = specificCategoryPostsItemData[position].relatedPostsContent
//            )

            BuiltInWebView.show(
                context = context,
                linkToLoad = specificCategoryPostsItemData[position].postLink,
                gradientColorOne = context.getColor(R.color.instagramOne),
                gradientColorTwo = context.getColor(R.color.instagramThree)
            )

        }

    }

}