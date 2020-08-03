/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/3/20 7:19 AM
 * Last modified 8/3/20 6:04 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package net.geekstools.supershortcuts.PRO.Utils.UI.Gesture

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import kotlin.math.abs

class SwipeGestureListener (private val context: Context,
                           private val gestureListenerInterface: GestureListenerInterface) : SimpleOnGestureListener() {


    private val gestureDetector: GestureDetector = GestureDetector(context, this@SwipeGestureListener)

    private var tapIndicator = false


    /**
     * Set Minimum Distance Of Swipe
     */
    var swipeMinDistance: Int  = 500
    /**
     * Set Maximum Distance Of Swipe
     */
    var swipeMaxDistance: Int  = 1000
    /**
     * Set Minimum Velocity Of Moving Finger
     */
    var swipeMinVelocity: Int  = 10

    private var mode: Int = GestureListenerConstants.MODE_DYNAMIC
    private var swipeMode: Int = 0


    override fun onFling(downMotionEvent: MotionEvent?, moveMotionEvent: MotionEvent?, initVelocityX: Float, initVelocityY: Float) : Boolean {

        var motionEventConsumed = false

        if (downMotionEvent != null && moveMotionEvent != null) {

            val xDistance = abs(downMotionEvent.x - moveMotionEvent.x)
            val yDistance = abs(downMotionEvent.y - moveMotionEvent.y)


            if (abs(initVelocityY) >= this.swipeMinVelocity && yDistance > this.swipeMinDistance && xDistance < yDistance) {//Vertical

                swipeMode = if (downMotionEvent.y > moveMotionEvent.y) {//Down -> Up

                    gestureListenerInterface.onSwipeGesture(
                            GestureConstants.SwipeVertical(
                                    GestureListenerConstants.SWIPE_UP), downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)
                    GestureListenerConstants.SWIPE_UP

                } else {//Up -> Down

                    gestureListenerInterface.onSwipeGesture(
                            GestureConstants.SwipeVertical(
                                    GestureListenerConstants.SWIPE_DOWN), downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)
                    GestureListenerConstants.SWIPE_DOWN

                }

                motionEventConsumed = true
            }

            if (abs(initVelocityX) >= this.swipeMinVelocity && xDistance > this.swipeMinDistance && yDistance < xDistance) {//Horizontal

                swipeMode = if (downMotionEvent.x > moveMotionEvent.x) {//Right -> Left

                    gestureListenerInterface.onSwipeGesture(
                            GestureConstants.SwipeHorizontal(
                                    GestureListenerConstants.SWIPE_LEFT), downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)
                    GestureListenerConstants.SWIPE_LEFT

                } else {//Left -> Right

                    gestureListenerInterface.onSwipeGesture(
                            GestureConstants.SwipeHorizontal(
                                    GestureListenerConstants.SWIPE_RIGHT), downMotionEvent, moveMotionEvent, initVelocityX, initVelocityY)
                    GestureListenerConstants.SWIPE_RIGHT

                }

                motionEventConsumed = true
            }

        }

        return motionEventConsumed
    }

    override fun onSingleTapConfirmed(motionEvent: MotionEvent): Boolean {

        return false
    }

    override fun onSingleTapUp(motionEvent: MotionEvent): Boolean {
        gestureListenerInterface.onSingleTapUp(motionEvent)

        return false
    }

    override fun onLongPress(motionEvent: MotionEvent) {

        gestureListenerInterface.onLongPress(motionEvent)
    }

    fun onTouchEvent(motionEvent: MotionEvent) {
        val motionEventConsumed = this@SwipeGestureListener.gestureDetector.onTouchEvent(motionEvent)

        if (this@SwipeGestureListener.mode == GestureListenerConstants.MODE_SOLID) {

            motionEvent.action = MotionEvent.ACTION_CANCEL

        } else if (this@SwipeGestureListener.mode == GestureListenerConstants.MODE_DYNAMIC) {

            if (motionEvent.action == GestureListenerConstants.ACTION_FAKE) {

                motionEvent.action = MotionEvent.ACTION_UP

            } else if (motionEventConsumed) {

                motionEvent.action = MotionEvent.ACTION_CANCEL

            } else if (this@SwipeGestureListener.tapIndicator) {

                motionEvent.action = MotionEvent.ACTION_DOWN

                this@SwipeGestureListener.tapIndicator = false
            }
        }
    }

    private fun swipeMode() : Int {

        return this@SwipeGestureListener.swipeMode
    }
}
