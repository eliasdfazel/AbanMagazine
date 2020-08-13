/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/2/20 10:38 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Utils

import android.annotation.SuppressLint
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringSystem
import com.google.android.material.button.MaterialButton

interface ImageResizingProcessAction {
    fun onImageViewReverted() {}
}

class ImageResizingProcess (private val animationImage: ImageView, private val controlView: LottieAnimationView, private val informationView: MaterialButton){

    var tensionValue = 975.0
    var frictionValue = 21.0

    var revertDelay: Long = 3975

    var recyclerView: RecyclerView? = null

    private var allowAnimation: Boolean = true

    @SuppressLint("ClickableViewAccessibility")
    fun start(imageResizingProcessAction: ImageResizingProcessAction) {

        recyclerView?.onFlingListener = object : RecyclerView.OnFlingListener() {

            override fun onFling(velocityX: Int, velocityY: Int): Boolean {

                allowAnimation = false

                return false
            }

        }

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                allowAnimation = when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {

                        true

                    }
                    else -> {

                        false

                    }
                }
            }

        })

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

                controlView.visibility = if (controlView.isShown) {

                    View.INVISIBLE

                } else {

                    View.VISIBLE.also {
                        controlView.playAnimation()
                    }

                }

                informationView.visibility = if (informationView.isShown) {

                    View.INVISIBLE

                } else {

                    View.VISIBLE

                }

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

                    if (allowAnimation) {

                        spring.endValue = (0.37)

                        animationImage.scaleType = ImageView.ScaleType.FIT_CENTER

                        Handler().postDelayed({

                            spring.endValue = (0.0)

                            animationImage.scaleType = ImageView.ScaleType.CENTER_CROP

                            imageResizingProcessAction.onImageViewReverted()

                        }, revertDelay)

                    }

                }
                MotionEvent.ACTION_UP -> {

                }
                MotionEvent.ACTION_CANCEL -> {

                    spring.endValue = (0.0)

                    animationImage.scaleType = ImageView.ScaleType.CENTER_CROP

                    imageResizingProcessAction.onImageViewReverted()

                }
            }

            true
        }

    }

}