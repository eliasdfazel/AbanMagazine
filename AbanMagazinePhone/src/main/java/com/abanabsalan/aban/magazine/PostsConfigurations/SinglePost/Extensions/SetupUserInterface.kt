/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/3/20 5:56 AM
 * Last modified 8/3/20 5:32 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.Extensions

import android.animation.Animator
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.abanabsalan.aban.magazine.PostsConfigurations.SinglePost.SinglePostUI.SinglePostView
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.BlogContent.Language
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractDominantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.extractVibrantColor
import com.abanabsalan.aban.magazine.Utils.UI.Colors.isColorDark
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayX
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayY
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlin.math.hypot

fun SinglePostView.setupUserInterface(postTitle: String, featureImageLink: String) {

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT

    val animatable = getDrawable(R.drawable.animated_post_menu_vector_icon) as Animatable
    animatable.start()

    Glide.with(applicationContext)
        .asDrawable()
        .load(animatable as Drawable)
        .into(postsViewUiBinding.postMenuIcon)

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.navigationBarColor = getColor(R.color.light)

            postsViewUiBinding.rootView.setBackgroundColor(getColor(R.color.light))

            postsViewUiBinding.postTitle.setTextColor(getColor(R.color.darker))

            postsViewUiBinding.postRecyclerView.setBackgroundColor(getColor(R.color.light))

            postsViewUiBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(getColor(R.color.light_transparent))
        }
        ThemeType.ThemeDark -> {

            window.navigationBarColor = getColor(R.color.dark)

            postsViewUiBinding.rootView.setBackgroundColor(getColor(R.color.dark))

            postsViewUiBinding.postTitle.setTextColor(getColor(R.color.lighter))

            postsViewUiBinding.postRecyclerView.setBackgroundColor(getColor(R.color.dark))

            postsViewUiBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(getColor(R.color.dark_transparent))

        }
    }

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

        val postMenuButtonCoordinatorLayoutLayoutParams = postsViewUiBinding.postMenuButton.layoutParams as CoordinatorLayout.LayoutParams
        postMenuButtonCoordinatorLayoutLayoutParams.anchorGravity = Gravity.BOTTOM or Gravity.START
        postMenuButtonCoordinatorLayoutLayoutParams.marginEnd = DpToInteger(applicationContext, 11)
        postsViewUiBinding.postMenuButton.layoutParams = postMenuButtonCoordinatorLayoutLayoutParams

        val postMenuIconCoordinatorLayoutLayoutParams = postsViewUiBinding.postMenuIcon.layoutParams as CoordinatorLayout.LayoutParams
        postMenuIconCoordinatorLayoutLayoutParams.anchorGravity = Gravity.BOTTOM or Gravity.START
        postMenuIconCoordinatorLayoutLayoutParams.marginEnd = DpToInteger(applicationContext, 11)
        postsViewUiBinding.postMenuIcon.layoutParams = postMenuIconCoordinatorLayoutLayoutParams

    } else {

        postsViewUiBinding.postTitle.setPadding(
            DpToInteger(applicationContext, 11),
            DpToInteger(applicationContext, 11),
            DpToInteger(applicationContext, 81),
            DpToInteger(applicationContext, 11)
        )

        val postMenuButtonCoordinatorLayoutLayoutParams = postsViewUiBinding.postMenuButton.layoutParams as CoordinatorLayout.LayoutParams
        postMenuButtonCoordinatorLayoutLayoutParams.anchorGravity = Gravity.BOTTOM or Gravity.END
        postMenuButtonCoordinatorLayoutLayoutParams.marginEnd = DpToInteger(applicationContext, 11)
        postsViewUiBinding.postMenuButton.layoutParams = postMenuButtonCoordinatorLayoutLayoutParams

        val postMenuIconCoordinatorLayoutLayoutParams = postsViewUiBinding.postMenuIcon.layoutParams as CoordinatorLayout.LayoutParams
        postMenuIconCoordinatorLayoutLayoutParams.anchorGravity = Gravity.BOTTOM or Gravity.END
        postMenuIconCoordinatorLayoutLayoutParams.marginEnd = DpToInteger(applicationContext, 11)
        postsViewUiBinding.postMenuIcon.layoutParams = postMenuIconCoordinatorLayoutLayoutParams

    }

    Glide.with(this@setupUserInterface)
        .asDrawable()
        .load(featureImageLink)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .listener(object : RequestListener<Drawable> {

            override fun onLoadFailed(glideException: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                runOnUiThread {

                    postsViewUiBinding.postFeatureImage.setImageDrawable(resource)

                    resource?.let {

                        dominantColor = extractDominantColor(applicationContext, it)
                        vibrantColor = extractVibrantColor(applicationContext, it)

                        window.setBackgroundDrawable(GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, arrayOf(vibrantColor!!, dominantColor!!).toIntArray()))

                        postsViewUiBinding.collapsingPostTopBar.contentScrim = GradientDrawable(
                            GradientDrawable.Orientation.RIGHT_LEFT, arrayOf(vibrantColor!!, dominantColor!!).toIntArray())

                        postsViewUiBinding.postMenuButton.backgroundTintList = ColorStateList.valueOf(vibrantColor!!)
                        postsViewUiBinding.postMenuButton.rippleColor = ColorStateList.valueOf(dominantColor!!)

                        if (isColorDark(dominantColor!!) && isColorDark(vibrantColor!!)) {
                            Log.d(this@setupUserInterface.javaClass.simpleName, "Dark Extracted Colors")

                            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        } else {
                            Log.d(this@setupUserInterface.javaClass.simpleName, "Light Extracted Colors")

                            window.decorView.systemUiVisibility = 0
                        }

                    }

                }

                return false
            }

        })
        .submit()

    setupPopupPreferencesClick()

}

fun SinglePostView.setupPopupPreferencesClick() {

    postsViewUiBinding.postMenuButton.setOnClickListener {


        if (postsViewUiBinding.preferencePopupInclude.root.isShown) {

            hidePopupPreferences()

        } else {


            showPopupPreferences()

        }

    }

}

fun SinglePostView.showPopupPreferences() {

    val finalRadius = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

    val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(postsViewUiBinding.preferencePopupInclude.root,
        (postsViewUiBinding.postMenuButton.x.toInt() + (postsViewUiBinding.postMenuButton.width / 2)),
        (postsViewUiBinding.postMenuButton.y.toInt() - (postsViewUiBinding.postMenuButton.height)),
        (postsViewUiBinding.postMenuButton.height.toFloat() / 2),
        finalRadius.toFloat())

    circularReveal.duration = 1111
    circularReveal.interpolator = AccelerateInterpolator()

    postsViewUiBinding.preferencePopupInclude.root.visibility = View.VISIBLE
    circularReveal.start()
    circularReveal.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            postsViewUiBinding.preferencePopupInclude.root.visibility = View.VISIBLE
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })

}

fun SinglePostView.hidePopupPreferences() {

    val finalRadius = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

    val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(postsViewUiBinding.preferencePopupInclude.root,
        (postsViewUiBinding.postMenuButton.x.toInt() + (postsViewUiBinding.postMenuButton.width / 2)),
        (postsViewUiBinding.postMenuButton.y.toInt() - (postsViewUiBinding.postMenuButton.height)),
        finalRadius.toFloat(),
        (postsViewUiBinding.postMenuButton.height.toFloat() / 2))

    circularReveal.duration = 1111
    circularReveal.interpolator = AccelerateInterpolator()

    circularReveal.start()
    circularReveal.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            postsViewUiBinding.preferencePopupInclude.root.visibility = View.INVISIBLE
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })

}