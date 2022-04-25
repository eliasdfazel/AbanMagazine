/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.FluidSlider

import android.util.Log

interface FluidSliderActionInterface {
    fun fluidSliderPosition(sliderPosition: Float) {}

    fun fluidSliderStarted() {}
    fun fluidSliderFinished() {}
}

class FluidSliderAction (private val fluidSliderActionInterface: FluidSliderActionInterface) {

    fun startListener(fluidSlider: FluidSlider) {

        fluidSlider.positionListener = { sliderPosition ->
            Log.d(this@FluidSliderAction.javaClass.simpleName, "$sliderPosition")

            fluidSlider.bubbleText = "${0 + (7  * sliderPosition).toInt()}"

            fluidSliderActionInterface.fluidSliderPosition(sliderPosition)
        }

        fluidSlider.beginTrackingListener = {

            fluidSliderActionInterface.fluidSliderStarted()

        }

        fluidSlider.endTrackingListener = {

            fluidSliderActionInterface.fluidSliderFinished()

        }

    }

}