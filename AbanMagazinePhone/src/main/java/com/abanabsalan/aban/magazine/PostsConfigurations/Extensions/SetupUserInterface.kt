/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 3:54 PM
 * Last modified 6/28/20 3:38 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Extensions

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import com.abanabsalan.aban.magazine.Utils.BlogContent.Language
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractDominantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractVibrantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.isColorDark
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun PostView.setupUserInterface(postTitle: String, featureImageLink: String) {

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT

    postsViewUiBinding.postTitle.text = Html.fromHtml(postTitle)

    postsViewUiBinding.postTitle.post {
        val postTopBarMarginLayoutParams = postsViewUiBinding.postTopBarMargin.layoutParams
        postTopBarMarginLayoutParams.height = postsViewUiBinding.postTitle.height
        postsViewUiBinding.postTopBarMargin.layoutParams = postTopBarMarginLayoutParams
    }

    val language: Language = Language()

    if (language.checkRtl(postTitle)) {

        postsViewUiBinding.postTitle.setPadding(
            DpToInteger(applicationContext, 81),
            DpToInteger(applicationContext, 11),
            DpToInteger(applicationContext, 11),
            DpToInteger(applicationContext, 11)
        )

        val coordinatorLayoutLayoutParams = postsViewUiBinding.postFavoriteItButton.layoutParams as CoordinatorLayout.LayoutParams
        coordinatorLayoutLayoutParams.anchorGravity = Gravity.BOTTOM or Gravity.START
        coordinatorLayoutLayoutParams.marginEnd = DpToInteger(applicationContext, 11)
        postsViewUiBinding.postFavoriteItButton.layoutParams = coordinatorLayoutLayoutParams

    } else {

        postsViewUiBinding.postTitle.setPadding(
            DpToInteger(applicationContext, 11),
            DpToInteger(applicationContext, 11),
            DpToInteger(applicationContext, 81),
            DpToInteger(applicationContext, 11)
        )

        val coordinatorLayoutLayoutParams = postsViewUiBinding.postFavoriteItButton.layoutParams as CoordinatorLayout.LayoutParams
        coordinatorLayoutLayoutParams.anchorGravity = Gravity.BOTTOM or Gravity.END
        coordinatorLayoutLayoutParams.marginEnd = DpToInteger(applicationContext, 11)
        postsViewUiBinding.postFavoriteItButton.layoutParams = coordinatorLayoutLayoutParams
    }

    Glide.with(this@setupUserInterface)
        .load(featureImageLink)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .listener(object : RequestListener<Drawable> {

            override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                return true
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                runOnUiThread {

                    postsViewUiBinding.postFeatureImage.setImageDrawable(resource)

                    resource?.let {

                        val dominantColor = extractDominantColor(applicationContext, it)
                        val vibrantColor = extractVibrantColor(applicationContext, it)

                        window.setBackgroundDrawable(GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, arrayOf(vibrantColor, dominantColor).toIntArray()))

                        postsViewUiBinding.collapsingPostTopBar.contentScrim = GradientDrawable(
                            GradientDrawable.Orientation.RIGHT_LEFT, arrayOf(vibrantColor, dominantColor).toIntArray())

                        postsViewUiBinding.postFavoriteItButton.backgroundTintList = ColorStateList.valueOf(vibrantColor)
                        postsViewUiBinding.postFavoriteItButton.rippleColor = ColorStateList.valueOf(dominantColor)

                        if (isColorDark(dominantColor) && isColorDark(vibrantColor)) {
                            Log.d(this@setupUserInterface.javaClass.simpleName, "Dark Extracted Colors")

                            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        } else {
                            Log.d(this@setupUserInterface.javaClass.simpleName, "Light Extracted Colors")

                            window.decorView.systemUiVisibility = 0
                        }

                    }

                }

                return true
            }

        })
        .submit()
}