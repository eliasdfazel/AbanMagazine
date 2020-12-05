/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/5/20 11:15 AM
 * Last modified 12/5/20 11:10 AM
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

        homePageViewBinding.indexInvocation.setOnClickListener {

            if (homePageViewBinding.indexViewInclude.root.isShown) {

                homePageViewBinding.indexViewInclude.root.visibility = View.INVISIBLE

            }

        }

        homePageViewBinding.indexInvocation.setOnLongClickListener {

            if (homePageViewBinding.indexViewInclude.root.isShown) {

                homePageViewBinding.indexViewInclude.root.visibility = View.INVISIBLE

            } else {

                doVibrate(context, 123)

                val finalRadius = hypot(displayX(context).toDouble(), displayY(context).toDouble())

                val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(homePageViewBinding.indexViewInclude.root,
                    homePageViewBinding.indexViewInclude.root.width,
                    homePageViewBinding.indexViewInclude.root.height / 2,
                    DpToInteger(context, 7).toFloat(),
                    finalRadius.toFloat())

                circularReveal.duration = 1111
                circularReveal.interpolator = AccelerateInterpolator()

                homePageViewBinding.indexViewInclude.root.visibility = View.VISIBLE

                circularReveal.start()

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