/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/26/20 3:50 PM
 * Last modified 6/26/20 3:42 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewIFrameAdapterViewHolder
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewImageAdapterViewHolder
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewParagraphAdapterViewHolder
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders.PostViewTextLinkAdapterViewHolder
import com.abanabsalan.aban.magazine.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class PostViewAdapter (private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val postItemsData: ArrayList<PostItemData> = ArrayList<PostItemData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            PostsDataParameters.PostItemsParameters.PostParagraph -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_paragraph, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsParameters.PostImage -> {

                PostViewImageAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_image, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsParameters.PostTextLink -> {

                PostViewTextLinkAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_text_link, viewGroup, false)
                )

            }
            PostsDataParameters.PostItemsParameters.PostIFrame -> {

                PostViewIFrameAdapterViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.post_view_content_item_i_frame, viewGroup, false)
                )

            }
            else -> {

                PostViewParagraphAdapterViewHolder(
                    LayoutInflater.from(context)
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

                postItemsData[position].postItemParagraph?.let {

                    (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.text = Html.fromHtml(it.paragraphText)
                }

            }
            PostsDataParameters.PostItemsParameters.PostImage -> {

                postItemsData[position].postItemImage?.let {

                    val drawableError: Drawable? = context.getDrawable(android.R.drawable.ic_menu_report_image)
                    drawableError?.setTint(context.getColor(R.color.pink))

                    val requestOptions = RequestOptions()
                        .error(drawableError)

                    Glide.with(context)
                        .load(it.imageLink)
                        .apply(requestOptions)
                        .transform(CenterInside(),RoundedCorners(13))
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into((viewHolder as PostViewImageAdapterViewHolder).postImage)

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