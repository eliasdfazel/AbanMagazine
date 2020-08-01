/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/1/20 5:13 AM
 * Last modified 8/1/20 5:13 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Utils

import android.view.MotionEvent
import android.widget.ImageView
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringSystem

class ImageResizingProcess (private val animationImage: ImageView){

    fun start() {

        val TENSION = 800.0
        val DAMPER = 20.0 //friction

        val springSystem = SpringSystem.create()
        val spring = springSystem.createSpring()

        spring.addListener(object : SimpleSpringListener(/*spring*/) {
            override fun onSpringUpdate(spring: Spring?) {
                val value = spring!!.currentValue.toFloat()
                val scale = 1f - (value * 0.5f)
                animationImage.scaleX = scale
                animationImage.scaleY = scale

                //animationImage.y = value
            }

            override fun onSpringEndStateChange(spring: Spring?) {

            }

            override fun onSpringAtRest(spring: Spring?) {

            }

            override fun onSpringActivate(spring: Spring?) {

            }

        })
        val springConfig = SpringConfig(TENSION, DAMPER)
        spring.springConfig = springConfig

        animationImage.setOnTouchListener { view, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_DOWN -> {
                    spring.endValue = (0.5)

                }
                MotionEvent.ACTION_UP -> {
                    spring.endValue = (0.0)

                }
            }
            false
        }

        animationImage.setOnClickListener {  }
    }

}