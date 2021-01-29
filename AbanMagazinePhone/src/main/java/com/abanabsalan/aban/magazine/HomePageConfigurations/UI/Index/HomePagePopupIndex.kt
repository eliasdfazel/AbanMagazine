/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 1/29/21 10:02 AM
 * Last modified 1/29/21 9:56 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Index

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.System.doVibrate
import com.abanabsalan.aban.magazine.Utils.UI.Display.DpToInteger
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayX
import com.abanabsalan.aban.magazine.Utils.UI.Display.displayY
import com.abanabsalan.aban.magazine.databinding.HomePageViewBinding
import kotlin.math.hypot
import kotlin.math.roundToInt

@SuppressLint("ClickableViewAccessibility")
class HomePagePopupIndex (val context: Context, val homePageViewBinding: HomePageViewBinding) {

    object IndexType {
        const val Categories = 0
        const val FeaturedPosts = 1
        const val ProductsShowcase = 2
        const val NewestPosts = 3
        const val RecommendedPosts = 4
        const val InstagramStories = 5
    }

    val indexTypePositionMap: HashMap<Int, Int> = HashMap<Int, Int>()

    init {

        homePageViewBinding.indexInvocation.bringToFront()

        homePageViewBinding.indexInvocation.setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {

                    if (homePageViewBinding.indexViewInclude.root.isShown) {

                        val animation = AnimationUtils.loadAnimation(context, R.anim.scale_down_move_left)
                        homePageViewBinding.indexViewInclude.root.startAnimation(animation)

                        animation.setAnimationListener(object : Animation.AnimationListener {

                            override fun onAnimationStart(animation: Animation?) {



                            }

                            override fun onAnimationEnd(animation: Animation?) {

                                homePageViewBinding.indexViewInclude.root.visibility = View.INVISIBLE

                            }

                            override fun onAnimationRepeat(animation: Animation?) {



                            }

                        })

                    } else {

                        doVibrate(context, 123)

                        val finalRadius = hypot(displayX(context).toDouble(), displayY(context).toDouble())

                        val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(homePageViewBinding.indexViewInclude.root,
                            homePageViewBinding.indexViewInclude.root.x.roundToInt(),
                            (homePageViewBinding.indexViewInclude.root.y + homePageViewBinding.indexInvocation.height).roundToInt(),
                            DpToInteger(context, 1).toFloat(),
                            finalRadius.toFloat())

                        circularReveal.duration = 1111
                        circularReveal.interpolator = AccelerateInterpolator()

                        homePageViewBinding.indexViewInclude.root.visibility = View.VISIBLE

                        circularReveal.start()

                    }

                }
                MotionEvent.ACTION_UP -> {



                }
                MotionEvent.ACTION_MOVE -> {



                }
            }

            true
        }

    }

    fun addCategoriesIndex() {

        homePageViewBinding.primaryCategoriesRecyclerView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.Categories] = homePageViewBinding.primaryCategoriesRecyclerView.y.roundToInt()

        }

        homePageViewBinding.indexViewInclude.categoriesIndex.setOnClickListener { view ->

            indexTypePositionMap[view.tag.toString().toInt()]?.let {

                homePageViewBinding.nestedScrollView.smoothScrollTo(0, 0)

            }

        }

    }

    fun addFeaturedPostsIndex() {

        homePageViewBinding.featuredPostsTextView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.FeaturedPosts] = homePageViewBinding.featuredPostsTextView.y.roundToInt()

        }

        homePageViewBinding.indexViewInclude.featuredPostsIndex.setOnClickListener { view ->

            indexTypePositionMap[view.tag.toString().toInt()]?.let {

                homePageViewBinding.nestedScrollView.smoothScrollTo(0, it.minus(homePageViewBinding.homepageTopBar.height))

            }

        }

    }

    fun addProductsShowcaseIndex() {

        homePageViewBinding.productShowcaseTextView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.ProductsShowcase] = homePageViewBinding.productShowcaseTextView.y.roundToInt()

        }

        homePageViewBinding.indexViewInclude.productsShowcaseIndex.setOnClickListener { view ->

            indexTypePositionMap[view.tag.toString().toInt()]?.let {

                homePageViewBinding.nestedScrollView.smoothScrollTo(0, it.minus(homePageViewBinding.homepageTopBar.height))

            }

        }

    }

    fun addNewestPostsIndex() {

        homePageViewBinding.newestPostsTextView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.NewestPosts] = homePageViewBinding.newestPostsTextView.y.roundToInt()

        }

        homePageViewBinding.indexViewInclude.newestPostsIndex.setOnClickListener { view ->

            indexTypePositionMap[view.tag.toString().toInt()]?.let {

                homePageViewBinding.nestedScrollView.smoothScrollTo(0, it.minus(homePageViewBinding.homepageTopBar.height))

            }

        }

    }

    fun addRecommendedPostsIndex() {

        homePageViewBinding.recommendedPostsRecyclerView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.RecommendedPosts] = homePageViewBinding.recommendedPostsRecyclerView.y.roundToInt()

        }

        homePageViewBinding.indexViewInclude.recommendedPostsIndex.setOnClickListener { view ->

            indexTypePositionMap[view.tag.toString().toInt()]?.let {

                homePageViewBinding.nestedScrollView.smoothScrollTo(0, it.minus(homePageViewBinding.homepageTopBar.height))

            }

        }

    }

    fun addInstagramStoriesIndex() {

        homePageViewBinding.instagramStoryHighlightsRecyclerView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.InstagramStories] = homePageViewBinding.bannerAdViewBottom.y.roundToInt()

        }

        homePageViewBinding.indexViewInclude.instagramStoriesIndex.setOnClickListener { view ->

            indexTypePositionMap[view.tag.toString().toInt()]?.let {

                homePageViewBinding.nestedScrollView.smoothScrollTo(0, homePageViewBinding.homepageScrollingContentView.height)

            }

        }

    }

}