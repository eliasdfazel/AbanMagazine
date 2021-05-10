/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 5/10/21, 8:05 AM
 * Last modified 5/10/21, 6:42 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view_content_item_author.view.*

class PostViewAuthorAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postAuthorName: TextView = view.postAuthorName
}