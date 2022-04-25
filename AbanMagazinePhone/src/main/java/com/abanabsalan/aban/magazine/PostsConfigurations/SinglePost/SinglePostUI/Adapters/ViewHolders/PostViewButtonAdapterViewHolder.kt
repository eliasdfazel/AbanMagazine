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

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.PostViewContentItemButtonBinding
import com.google.android.material.button.MaterialButton

class PostViewButtonAdapterViewHolder (postViewContentItemButtonBinding: PostViewContentItemButtonBinding) : RecyclerView.ViewHolder(postViewContentItemButtonBinding.root) {
    val rootViewItem: ConstraintLayout = postViewContentItemButtonBinding.rootViewItem
    val postButton: MaterialButton = postViewContentItemButtonBinding.postButton
}