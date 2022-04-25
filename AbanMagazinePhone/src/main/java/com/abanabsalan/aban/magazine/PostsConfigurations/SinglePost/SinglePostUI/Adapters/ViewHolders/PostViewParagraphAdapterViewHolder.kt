/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.PostViewContentItemParagraphBinding

class PostViewParagraphAdapterViewHolder (view: PostViewContentItemParagraphBinding) : RecyclerView.ViewHolder(view.root) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postParagraph: AppCompatTextView = view.postParagraph
}