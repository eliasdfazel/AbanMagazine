/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/25/20 2:00 PM
 * Last modified 6/25/20 1:39 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters

import android.content.Context
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

                    (viewHolder as PostViewParagraphAdapterViewHolder).postParagraph.text = it.paragraphText
                }

            }
            PostsDataParameters.PostItemsParameters.PostImage -> {



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