/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/1/20 5:52 AM
 * Last modified 8/1/20 5:50 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Utils

import android.annotation.SuppressLint
import android.os.Handler
import android.view.MotionEvent
import android.widget.ImageView
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringSystem

interface ImageResizingProcessAction {
    fun onImageViewClick() {}
}

class ImageResizingProcess (private val animationImage: ImageView){

    val tensionValue = 975.0
    val frictionValue = 21.0

    val delayHandler: Handler = Handler()
    lateinit var delayRunnable: Runnable

    @SuppressLint("ClickableViewAccessibility")
    fun start(imageResizingProcessAction: ImageResizingProcessAction) {

        val springSystem = SpringSystem.create()
        val spring = springSystem.createSpring()

        spring.addListener(object : SimpleSpringListener() {
            override fun onSpringUpdate(spring: Spring?) {

                val value = spring?.currentValue!!.toFloat()
                val scale = 1f - (value * 0.5f)

                animationImage.scaleX = scale
                animationImage.scaleY = scale

            }

            override fun onSpringEndStateChange(spring: Spring?) {

            }

            override fun onSpringAtRest(spring: Spring?) {

            }

            override fun onSpringActivate(spring: Spring?) {

            }

        })

        val springConfig = SpringConfig(tensionValue, frictionValue)
        spring.springConfig = springConfig

        animationImage.setOnTouchListener { view, motionEvent ->

            when (motionEvent?.action) {
                MotionEvent.ACTION_DOWN -> {
                    spring.endValue = (0.13)

                    animationImage.scaleType = ImageView.ScaleType.FIT_CENTER

                    delayRunnable = Runnable {

                        imageResizingProcessAction.onImageViewClick()

                    }

                    delayHandler.postDelayed(delayRunnable, 3333)

                }
                MotionEvent.ACTION_UP -> {
                    delayHandler.removeCallbacks(delayRunnable)

                    spring.endValue = (0.0)

                    animationImage.scaleType = ImageView.ScaleType.CENTER_CROP

                }
                MotionEvent.ACTION_CANCEL -> {
                    delayHandler.removeCallbacks(delayRunnable)

                    spring.endValue = (0.0)

                    animationImage.scaleType = ImageView.ScaleType.CENTER_CROP

                }
            }

            true
        }

    }

}