/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 1/29/21 7:55 AM
 * Last modified 1/29/21 7:47 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.NewestPosts

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class FeaturedPostsSlider {

    lateinit var initialSliderJob: Job

    var initialSliderRange: IntRange = IntRange(0, 0)

    var currentSliderPosition = 0

    fun startSliding(recyclerView: RecyclerView, positionRange: IntRange) = CoroutineScope(Dispatchers.Main).async {

        delay(3579)

        positionRange.forEach { position ->
            Log.d(this@FeaturedPostsSlider.javaClass.simpleName, "Going To New Position: ${position}")

            delay(3579)

            currentSliderPosition = position

            recyclerView.smoothScrollToPosition(position)

            resetSlidingCheckpoint(recyclerView, positionRange, position)

        }

    }

    private fun resetSlidingCheckpoint(recyclerView: RecyclerView, positionRange: IntRange, currentPosition: Int) {

        if (initialSliderJob.isActive) {
            initialSliderJob.cancel()
        }

        if (currentPosition == positionRange.last) {
            Log.d(this@FeaturedPostsSlider.javaClass.simpleName, "Sliding Reset")

            currentSliderPosition = 0

            recyclerView.smoothScrollToPosition(0)

            initialSliderJob = startSliding(recyclerView, IntRange(0, positionRange.last))

        }

    }

}