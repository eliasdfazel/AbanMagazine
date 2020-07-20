/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/19/20 5:58 PM
 * Last modified 7/19/20 5:56 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory

import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class SpecificCategoryAdapter (private val context: HomePage, private val themeLightDark: Int): RecyclerView.Adapter<SpecificCategoryViewHolder>() {

    val specificCategoryPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SpecificCategoryViewHolder {

        return SpecificCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_specific_category_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return specificCategoryPostsItemData.size
    }

    override fun onBindViewHolder(specificCategoryViewHolder: SpecificCategoryViewHolder, position: Int) {

        when (themeLightDark) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.red))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
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

        specificCategoryViewHolder.postTitleView.text = Html.fromHtml(specificCategoryPostsItemData[position].postTitle)
        specificCategoryViewHolder.postExcerptView.text = Html.fromHtml(specificCategoryPostsItemData[position].postExcerpt)

        specificCategoryViewHolder.rootViewItem.setOnClickListener {

            PostView.show(
                context = context,
                featuredImageSharedElement = specificCategoryViewHolder.postFeatureImageView as AppCompatImageView,
                postFeaturedImage = specificCategoryPostsItemData[position].postFeaturedImage,
                postTitle = specificCategoryPostsItemData[position].postTitle,
                postContent = specificCategoryPostsItemData[position].postContent
            )

        }

    }

}