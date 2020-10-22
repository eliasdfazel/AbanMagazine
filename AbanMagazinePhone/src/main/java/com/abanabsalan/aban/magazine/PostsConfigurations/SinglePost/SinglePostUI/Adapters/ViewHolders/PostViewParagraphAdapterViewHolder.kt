/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 10/22/20 9:14 AM
 * Last modified 10/22/20 9:14 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view_content_item_paragraph.view.*

class PostViewParagraphAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postParagraph: AppCompatTextView = view.postParagraph
}