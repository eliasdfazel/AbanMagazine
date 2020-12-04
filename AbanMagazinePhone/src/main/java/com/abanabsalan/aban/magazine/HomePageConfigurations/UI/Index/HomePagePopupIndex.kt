/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 12/4/20 10:53 AM
 * Last modified 12/4/20 10:53 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Index

import android.annotation.SuppressLint
import android.view.MotionEvent
import com.abanabsalan.aban.magazine.databinding.HomePageViewBinding
import kotlin.math.roundToInt

@SuppressLint("ClickableViewAccessibility")
class HomePagePopupIndex (val homePageViewBinding: HomePageViewBinding) {

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

        homePageViewBinding.indexInvocation.setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {



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