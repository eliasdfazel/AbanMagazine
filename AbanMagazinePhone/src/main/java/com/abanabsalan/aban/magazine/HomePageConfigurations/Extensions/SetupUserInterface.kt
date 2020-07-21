/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/20/20 6:21 PM
 * Last modified 7/20/20 6:21 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayX
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayY
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Display.statusBarHeight
import com.abanabsalan.aban.magazine.Utils.UI.Theme.ThemeType
import kotlin.math.hypot

fun HomePage.setupUserInterface() {

    val topBarLayoutParams = homePageViewBinding.homepageTopBar.layoutParams as ConstraintLayout.LayoutParams
    topBarLayoutParams.height = homePageViewBinding.homepageTopBar.height + statusBarHeight(applicationContext)
    homePageViewBinding.homepageTopBar.layoutParams = topBarLayoutParams

    val bottomBarBlurViewLayoutParams = homePageViewBinding.blurViewBottomBar.layoutParams as ConstraintLayout.LayoutParams
    bottomBarBlurViewLayoutParams.height = navigationBarHeight(applicationContext)
    homePageViewBinding.blurViewBottomBar.layoutParams = bottomBarBlurViewLayoutParams

    homePageViewBinding.homepageScrollingContentView.setPadding(0,
        homePageViewBinding.homepageTopBar.height + statusBarHeight(applicationContext),
        0, 0)

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.statusBarColor = getColor(R.color.default_color_light_transparent)
            window.navigationBarColor = getColor(R.color.default_color_light_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_light_transparent))
            homePageViewBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_light_transparent))

            homePageViewBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(getColor(R.color.default_color_light_transparent))

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.default_color_dark_transparent)
            window.navigationBarColor = getColor(R.color.default_color_dark_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))
            homePageViewBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))

            homePageViewBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(getColor(R.color.default_color_dark_transparent))

        }
    }

    setupPopupPreferencesClick()

}

fun HomePage.setupPopupPreferencesClick() {

    homePageViewBinding.optionMenus.setOnClickListener {


        if (homePageViewBinding.preferencePopupInclude.root.isShown) {

            hidePopupPreferences()

        } else {


            showPopupPreferences()

        }

    }

}

fun HomePage.showPopupPreferences() {

    val finalRadius = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

    val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(homePageViewBinding.preferencePopupInclude.root,
        (homePageViewBinding.optionMenus.x.toInt() + (homePageViewBinding.optionMenus.width / 2)),
        (homePageViewBinding.optionMenus.y.toInt() - (homePageViewBinding.optionMenus.height)),
        (homePageViewBinding.optionMenus.height.toFloat() / 2),
        finalRadius.toFloat())

    circularReveal.duration = 1111
    circularReveal.interpolator = AccelerateInterpolator()

    homePageViewBinding.preferencePopupInclude.root.visibility = View.VISIBLE
    circularReveal.start()
    circularReveal.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            homePageViewBinding.preferencePopupInclude.root.visibility = View.VISIBLE
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })

}

fun HomePage.hidePopupPreferences() {

    val finalRadius = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

    val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(homePageViewBinding.preferencePopupInclude.root,
        (homePageViewBinding.optionMenus.x.toInt() + (homePageViewBinding.optionMenus.width / 2)),
        (homePageViewBinding.optionMenus.y.toInt() - (homePageViewBinding.optionMenus.height)),
        finalRadius.toFloat(),
        (homePageViewBinding.optionMenus.height.toFloat() / 2))

    circularReveal.duration = 1111
    circularReveal.interpolator = AccelerateInterpolator()

    circularReveal.start()
    circularReveal.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            homePageViewBinding.preferencePopupInclude.root.visibility = View.INVISIBLE
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })

}