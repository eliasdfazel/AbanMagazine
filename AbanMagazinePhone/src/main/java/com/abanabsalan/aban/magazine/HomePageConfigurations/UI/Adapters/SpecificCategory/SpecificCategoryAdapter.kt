/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/4/20 12:17 PM
 * Last modified 7/4/20 12:17 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory

import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class SpecificCategoryAdapter (private val context: HomePage, private val themeLightDark: Int): RecyclerView.Adapter<SpecificCategoryViewHolder>() {

    val postsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SpecificCategoryViewHolder {

        return SpecificCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_specific_category_item, viewGroup, false))
    }

    override fun getItemCount(): Int {

        return postsItemData.size
    }

    override fun onBindViewHolder(specificCategoryViewHolder: SpecificCategoryViewHolder, position: Int) {

        println(">>>>>>>>>>>>>>>> 3 " + postsItemData[position])

        when (themeLightDark) {
            ThemeType.ThemeLight -> {



            }
            ThemeType.ThemeDark -> {



            }
        }

        val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
        drawableError?.setTint(context.getColor(R.color.pink))

        val requestOptions = RequestOptions()
            .error(drawableError)

        Glide.with(context)
            .load(postsItemData[position].postFeaturedImage)
            .apply(requestOptions)
            .transform(CenterInside(), RoundedCorners(13))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    context.runOnUiThread {
                        specificCategoryViewHolder.postFeatureImageView.setImageDrawable(resource)
                    }

                    return true
                }

            })
            .submit()

        specificCategoryViewHolder.postTitleView.text = Html.fromHtml(postsItemData[position].postTitle)
        specificCategoryViewHolder.postExcerptView.text = Html.fromHtml(postsItemData[position].postExcerpt)

    }

}