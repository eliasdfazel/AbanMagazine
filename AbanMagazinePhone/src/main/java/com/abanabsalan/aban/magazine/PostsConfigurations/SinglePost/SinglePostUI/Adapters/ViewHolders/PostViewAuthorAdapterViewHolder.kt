/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:29 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.PostViewContentItemAuthorBinding

class PostViewAuthorAdapterViewHolder (postViewContentItemAuthorBinding: PostViewContentItemAuthorBinding) : RecyclerView.ViewHolder(postViewContentItemAuthorBinding.root) {
    val rootViewItem: ConstraintLayout = postViewContentItemAuthorBinding.rootViewItem

    val postAuthorName: TextView = postViewContentItemAuthorBinding.postAuthorName
    val postAuthorBiography: TextView = postViewContentItemAuthorBinding.postAuthorBiography
    val postAuthorImage: ImageView = postViewContentItemAuthorBinding.postAuthorImage
}