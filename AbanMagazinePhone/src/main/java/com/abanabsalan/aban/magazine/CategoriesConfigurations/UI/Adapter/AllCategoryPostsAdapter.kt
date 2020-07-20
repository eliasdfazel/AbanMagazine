/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/20/20 1:37 PM
 * Last modified 7/20/20 1:37 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Adapter

import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class AllCategoryPostsAdapter (private val context: AllCategoryPosts, private val themeLightDark: Int): RecyclerView.Adapter<AllCategoryPostsViewHolder>() {

    val postsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AllCategoryPostsViewHolder {

        return AllCategoryPostsViewHolder(LayoutInflater.from(context).inflate(R.layout.all_category_posts_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return postsItemData.size
    }

    override fun onBindViewHolder(allCategoryPostsViewHolder: AllCategoryPostsViewHolder, position: Int) {

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

        allCategoryPostsViewHolder.postTitleView.text = Html.fromHtml(postsItemData[position].postTitle)
        allCategoryPostsViewHolder.postExcerptView.text = Html.fromHtml(postsItemData[position].postExcerpt)

        Glide.with(context)
            .asGif()
            .load(R.raw.share_animation)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(CenterCrop(), RoundedCorners(23))
            .into(allCategoryPostsViewHolder.shareIcon)


        allCategoryPostsViewHolder.rootViewItem.setOnClickListener {

            PostView.show(
                context = context,
                featuredImageSharedElement = allCategoryPostsViewHolder.postFeatureImageView as AppCompatImageView,
                postFeaturedImage = postsItemData[position].postFeaturedImage,
                postTitle = postsItemData[position].postTitle,
                postContent = postsItemData[position].postContent
            )

        }

    }

}