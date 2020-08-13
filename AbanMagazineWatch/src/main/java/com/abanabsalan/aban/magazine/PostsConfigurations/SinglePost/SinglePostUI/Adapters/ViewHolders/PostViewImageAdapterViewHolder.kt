/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.Adapters.ViewHolders

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.post_view_content_item_image.view.*
import net.geeksempire.loadingspin.SpinKitView
import net.geekstools.imageview.customshapes.ShapesImage

class PostViewImageAdapterViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postImage: ShapesImage = view.postImage
    val showFullScreen: LottieAnimationView = view.showFullScreen
    val showFullScreenInformation: MaterialButton = view.showFullScreenInformation
    val postImageLoading: SpinKitView = view.postImageLoading
}