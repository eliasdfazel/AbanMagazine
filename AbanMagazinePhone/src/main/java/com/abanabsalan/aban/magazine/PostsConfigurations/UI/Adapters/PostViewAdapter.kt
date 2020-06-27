/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/26/20 7:05 PM
 * Last modified 6/26/20 4:40 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters

import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewIFrameAdapterViewHolder
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewImageAdapterViewHolder
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewParagraphAdapterViewHolder
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewTextLinkAdapterViewHolder
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class PostViewAdapter (private val postViewContext: PostView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val postItemsData: ArrayList<PostItemData> = ArrayList<PostItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            PostsDataParameters.PostItemsParameters.PostParagraph -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(postViewContext)
                        .inflate(R.layout.post_view_content_item_paragraph, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsParameters.PostImage -> {

                PostViewImageAdapterViewHolder(
                    LayoutInflater.from(postViewContext)
                        .inflate(R.layout.post_view_content_item_image, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsParameters.PostTextLink -> {

                PostViewTextLinkAdapterViewHolder(
                    LayoutInflater.from(postViewContext)
                        .inflate(R.layout.post_view_content_item_text_link, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsParameters.PostIFrame -> {

                PostViewIFrameAdapterViewHolder(
                    LayoutInflater.from(postViewContext)
                        .inflate(R.layout.post_view_content_item_i_frame, viewGroup, false)
                )

            }
            else -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(postViewContext)
                        .inflate(R.layout.post_view_content_item_paragraph, viewGroup, false)
                )

            }

        }

    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)

        return postItemsData[position].dataType
    }

    override fun getItemCount(): Int {

        return postItemsData.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {


        when (postItemsData[position].dataType) {

            PostsDataParameters.PostItemsParameters.PostParagraph -> {

                viewHolder as PostViewParagraphAdapterViewHolder

                postItemsData[position].postItemParagraph?.let {

                    viewHolder.postParagraph.text = Html.fromHtml(it.paragraphText)
                }

            }
            PostsDataParameters.PostItemsParameters.PostImage -> {

                viewHolder as PostViewImageAdapterViewHolder

                postItemsData[position].postItemImage?.let {

                    val drawableError: Drawable? = postViewContext.getDrawable(android.R.drawable.ic_menu_report_image)
                    drawableError?.setTint(postViewContext.getColor(R.color.pink))

                    val requestOptions = RequestOptions()
                        .error(drawableError)

                    Glide.with(postViewContext)
                        .load(it.imageLink)
                        .apply(requestOptions)
                        .transform(CenterInside(),RoundedCorners(13))
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .listener(object : RequestListener<Drawable> {

                            override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                                return true
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                                postViewContext.runOnUiThread {
                                    viewHolder.postImage.setImageDrawable(resource)
                                    viewHolder.postImageLoading.visibility = View.GONE
                                }

                                return true
                            }

                        })
                        .submit()

                }

            }
            PostsDataParameters.PostItemsParameters.PostTextLink -> {



            }
            PostsDataParameters.PostItemsParameters.PostIFrame -> {



            }
            else -> {

            }

        }

    }

}