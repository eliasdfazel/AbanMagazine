/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/18/20 11:03 AM
 * Last modified 7/18/20 10:47 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view_content_item_sub_title.view.*

class PostViewSubTitleAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postSubTitle: TextView = view.postSubTitle
}