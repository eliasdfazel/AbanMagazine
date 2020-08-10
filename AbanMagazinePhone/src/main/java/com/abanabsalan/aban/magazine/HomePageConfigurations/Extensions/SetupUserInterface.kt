/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 12:49 AM
 * Last modified 8/10/20 12:32 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import android.animation.Animator
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Display.*
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

    Handler().postDelayed({

        homePageViewBinding.optionMenus.playAnimation()
        homePageViewBinding.searchView.playAnimation()

    }, 1357)

    setupPopupPreferencesClick()

}

fun HomePage.setupTheme() {

    when (overallTheme.checkThemeLightDark()) {
        ThemeType.ThemeLight -> {

            window.statusBarColor = getColor(R.color.default_color_light_transparent)
            window.navigationBarColor = getColor(R.color.default_color_light_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.light))

            homePageViewBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_light_transparent))
            homePageViewBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_light_transparent))

            homePageViewBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(getColor(R.color.default_color_light_transparent))

            homePageViewBinding.featuredPostsTextView.setTextColor(getColor(R.color.darker))
            homePageViewBinding.newestPostsTextView.setTextColor(getColor(R.color.darker))

            homePageViewBinding.optionMenus.setAnimation(R.raw.lady_settings_light)

        }
        ThemeType.ThemeDark -> {

            window.statusBarColor = getColor(R.color.default_color_dark_transparent)
            window.navigationBarColor = getColor(R.color.default_color_dark_transparent)
            window.decorView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.rootView.setBackgroundColor(getColor(R.color.dark))

            homePageViewBinding.blurViewTopBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))
            homePageViewBinding.blurViewBottomBar.setOverlayColor(getColor(R.color.default_color_dark_transparent))

            homePageViewBinding.preferencePopupInclude.preferencesBlurView.setOverlayColor(getColor(R.color.default_color_dark_transparent))

            homePageViewBinding.featuredPostsTextView.setTextColor(getColor(R.color.lighter))
            homePageViewBinding.newestPostsTextView.setTextColor(getColor(R.color.lighter))

            homePageViewBinding.optionMenus.setAnimation(R.raw.lady_settings_dark)

        }
    }

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

fun HomePage.setupRefreshView() {

    val finalRadiusShow = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

    val circularRevealShow: Animator = ViewAnimationUtils.createCircularReveal(homePageViewBinding.refreshContentsInclude.root,
        displayX(applicationContext) / 2,
        0,
        DpToInteger(applicationContext, 7).toFloat(),
        finalRadiusShow.toFloat())

    circularRevealShow.duration = 777
    circularRevealShow.interpolator = AccelerateInterpolator()

    homePageViewBinding.refreshContentsInclude.root.visibility = View.VISIBLE
    circularRevealShow.start()
    circularRevealShow.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            homePageViewBinding.refreshContentsInclude.root.visibility = View.VISIBLE

            homePageViewBinding.refreshContentsInclude.refreshAnimationView.playAnimation()
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationStart(animation: Animator?) {

        }
    })

    Handler().postDelayed({

        val finalRadiusHide = hypot(displayX(applicationContext).toDouble(), displayY(applicationContext).toDouble())

        val circularRevealHide: Animator = ViewAnimationUtils.createCircularReveal(homePageViewBinding.refreshContentsInclude.root,
            displayX(applicationContext) / 2,
            0,
            finalRadiusHide.toFloat(),
            DpToInteger(applicationContext, 7).toFloat())

        circularRevealHide.duration = 777
        circularRevealHide.interpolator = AccelerateInterpolator()

        circularRevealHide.start()
        circularRevealHide.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                homePageViewBinding.refreshContentsInclude.root.visibility = View.GONE

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }
        })

    }, (1000 * 60/*One Minute*/) * 1)

    Handler().postDelayed({

        updateDelay = true

    }, (1000 * 60/*One Minute*/) * 15)

}