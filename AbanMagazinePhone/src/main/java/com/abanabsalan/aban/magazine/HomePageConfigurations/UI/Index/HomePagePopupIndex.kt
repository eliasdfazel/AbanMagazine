/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/5/20 4:53 AM
 * Last modified 12/5/20 4:53 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Index

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
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

    val indexTypePositionMap = HashMap<Int, Int>()

    init {

        homePageViewBinding.indexInvocation.setOnClickListener {

            if (homePageViewBinding.indexViewInclude.root.isShown) {

                homePageViewBinding.indexViewInclude.root.visibility = View.INVISIBLE

            }

        }

        homePageViewBinding.indexInvocation.setOnLongClickListener {

            if (homePageViewBinding.indexViewInclude.root.isShown) {

                homePageViewBinding.indexViewInclude.root.visibility = View.INVISIBLE

            } else {

                val finalRadius = hypot(displayX(context).toDouble(), displayY(context).toDouble())

                val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(homePageViewBinding.indexViewInclude.root,
                    (homePageViewBinding.indexInvocation.x).roundToInt() + (homePageViewBinding.indexInvocation.height / 2),
                    (homePageViewBinding.indexViewInclude.root.y).roundToInt(),
                    DpToInteger(context, 13).toFloat(),
                    finalRadius.toFloat())

                circularReveal.duration = 1111
                circularReveal.interpolator = AccelerateInterpolator()

                homePageViewBinding.indexViewInclude.root.visibility = View.VISIBLE

                circularReveal.start()
                circularReveal.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {

                    }

                    override fun onAnimationEnd(animation: Animator?) {

                    }

                    override fun onAnimationCancel(animation: Animator?) {

                    }

                    override fun onAnimationStart(animation: Animator?) {

                    }
                })

            }

            true
        }

    }

    fun addCategoriesIndex() {

        homePageViewBinding.primaryCategoriesRecyclerView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.Categories] = homePageViewBinding.primaryCategoriesRecyclerView.y.roundToInt()

        }

    }

    fun addFeaturedPostsIndex() {

        homePageViewBinding.featuredPostsTextView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.FeaturedPosts] = homePageViewBinding.featuredPostsTextView.y.roundToInt()

        }

    }

    fun addProductsShowcaseIndex() {

        homePageViewBinding.productShowcaseTextView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.ProductsShowcase] = homePageViewBinding.productShowcaseTextView.y.roundToInt()

        }

    }

    fun addNewestPostsIndex() {

        homePageViewBinding.newestPostsTextView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.NewestPosts] = homePageViewBinding.newestPostsTextView.y.roundToInt()

        }

    }

    fun addRecommendedPostsIndex() {

        homePageViewBinding.recommendedPostsRecyclerView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.RecommendedPosts] = homePageViewBinding.recommendedPostsRecyclerView.y.roundToInt()

        }

    }

    fun addInstagramStoriesIndex() {

        homePageViewBinding.instagramStoryHighlightsRecyclerView.post {

            indexTypePositionMap[HomePagePopupIndex.IndexType.InstagramStories] = homePageViewBinding.instagramStoryHighlightsRecyclerView.y.roundToInt()

        }

    }

    fun scrollToPosition(yPosition: Int) {

        homePageViewBinding.nestedScrollView.smoothScrollTo(0 , yPosition)

    }

}