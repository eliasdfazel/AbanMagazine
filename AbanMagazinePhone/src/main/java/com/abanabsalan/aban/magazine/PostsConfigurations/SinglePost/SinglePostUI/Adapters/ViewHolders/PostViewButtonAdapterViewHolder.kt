/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/1/20 11:16 AM
 * Last modified 9/1/20 11:13 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.post_view_content_item_button.view.*

class PostViewButtonAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postButton: MaterialButton = view.postButton
}