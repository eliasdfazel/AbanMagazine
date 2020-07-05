/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/5/20 3:47 PM
 * Last modified 7/5/20 3:11 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.NewestPosts

import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
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

class NewestPostsAdapter (private val context: HomePage, private val themeLightDark: Int): RecyclerView.Adapter<NewestPostsViewHolder>() {

    val newestPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewestPostsViewHolder {

        return NewestPostsViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_newest_posts_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return newestPostsItemData.size
    }

    override fun onBindViewHolder(newestPostsViewHolder: NewestPostsViewHolder, position: Int) {

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
            .load(newestPostsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
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

            PostView.show(
                context = context,
                postFeaturedImage = newestPostsItemData[position].postFeaturedImage,
                postTitle = newestPostsItemData[position].postTitle,
                postContent = newestPostsItemData[position].postContent
            )

        }

    }

}