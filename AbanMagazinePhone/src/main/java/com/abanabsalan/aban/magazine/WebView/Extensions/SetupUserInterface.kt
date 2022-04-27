/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/27/22, 6:21 AM
 * Last modified 4/27/22, 6:20 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.WebView.Extensions

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayX
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayY
import com.abanabsalan.aban.magazine.WebView.BuiltInWebView
import kotlin.math.hypot

fun BuiltInWebView.showPopupSearches() {

    if (browserViewBinding.preferencePopupInclude.root.isShown) {
        browserViewBinding.preferencePopupInclude.root.visibility = View.INVISIBLE
    }

    val finalRadius = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

    val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(browserViewBinding.searchPopupInclude.root,
        (browserViewBinding.searchView.x.toInt() + (browserViewBinding.searchView.width / 2)),
        (browserViewBinding.searchView.y.toInt() - (browserViewBinding.searchView.height)),
        (browserViewBinding.searchView.height.toFloat() / 2),
        finalRadius.toFloat())

    circularReveal.duration = 1111
    circularReveal.interpolator = AccelerateInterpolator()

    browserViewBinding.searchPopupInclude.root.visibility = View.VISIBLE
    circularReveal.start()
    circularReveal.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            browserViewBinding.searchPopupInclude.root.visibility = View.VISIBLE
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })

}

fun BuiltInWebView.hidePopupSearches() {

    val finalRadius = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

    val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(browserViewBinding.searchPopupInclude.root,
        (browserViewBinding.searchView.x.toInt() + (browserViewBinding.searchView.width / 2)),
        (browserViewBinding.searchView.y.toInt() - (browserViewBinding.searchView.height)),
        finalRadius.toFloat(),
        (browserViewBinding.searchView.height.toFloat() / 2))

    circularReveal.duration = 1111
    circularReveal.interpolator = AccelerateInterpolator()

    circularReveal.start()
    circularReveal.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            browserViewBinding.searchPopupInclude.root.visibility = View.INVISIBLE
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })

}