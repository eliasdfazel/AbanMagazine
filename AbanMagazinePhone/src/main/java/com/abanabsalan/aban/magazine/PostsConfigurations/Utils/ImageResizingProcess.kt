/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/23/20 9:50 AM
 * Last modified 9/23/20 9:50 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Utils

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
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

class ImageResizingProcess (private val animationImage: ImageView, private val controlView: LottieAnimationView, private val informationView: MaterialButton) {

    var tensionValue = 975.0
    var frictionValue = 21.0

    var revertDelay: Long = 3975

    var pressDelay: Long = 777

    var recyclerView: RecyclerView? = null

    private var runnablePressHold: Runnable? = null
    private val handlerPressHold: Handler = Handler(Looper.getMainLooper())

    private var allowAnimationPress: Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    fun start(imageResizingProcessAction: ImageResizingProcessAction) {

        recyclerView?.onFlingListener = object : RecyclerView.OnFlingListener() {

            override fun onFling(velocityX: Int, velocityY: Int): Boolean {

                return false
            }

        }

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {

                    }
                    else -> {

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
                    Log.d(this@ImageResizingProcess.javaClass.simpleName, "Image Touch Down")

                    allowAnimationPress = true

                    runnablePressHold = Runnable {
                        if (allowAnimationPress) {

                            spring.endValue = (0.37)

                            animationImage.scaleType = ImageView.ScaleType.FIT_CENTER

                            Handler(Looper.getMainLooper()).postDelayed({

                                spring.endValue = (0.0)

                                animationImage.scaleType = ImageView.ScaleType.CENTER_CROP

                                imageResizingProcessAction.onImageViewReverted()

                            }, revertDelay)

                        }
                    }

                    runnablePressHold?.let {
                        handlerPressHold.postDelayed(it, pressDelay)
                    }

                }
                MotionEvent.ACTION_UP -> {
                    Log.d(this@ImageResizingProcess.javaClass.simpleName, "Image Touch Up")

                    allowAnimationPress = false

                    runnablePressHold?.let {
                        handlerPressHold.removeCallbacks(it)
                    }

                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d(this@ImageResizingProcess.javaClass.simpleName, "Image Touch Cancel")

                    allowAnimationPress = false

                    runnablePressHold?.let {
                        handlerPressHold.removeCallbacks(it)
                    }

                    spring.endValue = (0.0)

                    animationImage.scaleType = ImageView.ScaleType.CENTER_CROP

                    imageResizingProcessAction.onImageViewReverted()

                }
            }

            true
        }

    }

}