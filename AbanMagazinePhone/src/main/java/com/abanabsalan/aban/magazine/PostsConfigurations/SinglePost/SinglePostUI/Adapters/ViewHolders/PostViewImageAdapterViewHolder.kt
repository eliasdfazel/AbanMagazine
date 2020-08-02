/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/2/20 5:50 AM
 * Last modified 8/2/20 5:50 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.post_view_content_item_image.view.*
import net.geeksempire.loadingspin.SpinKitView
import net.geekstools.imageview.customshapes.ShapesImage

class PostViewImageAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postImage: ShapesImage = view.postImage
    val showFullScreen: LottieAnimationView = view.showFullScreen
    val postImageLoading: SpinKitView = view.postImageLoading
}