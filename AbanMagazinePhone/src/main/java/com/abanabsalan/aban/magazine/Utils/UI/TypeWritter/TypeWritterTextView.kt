/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.TypeWritter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TypeWriterTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private var inputText: CharSequence? = null

    private var inputTextIndex = 0

    private var animationDelay: Long = 71
    private val animationHandler: Handler = Handler(Looper.getMainLooper())

    private val characterAdder: Runnable = object : Runnable {

        override fun run() {

            inputText?.let {

                text = it.subSequence(0, inputTextIndex++)

                if (inputTextIndex <= it.length) {
                    animationHandler.postDelayed(this, animationDelay)
                }

            }

        }
    }

    fun setAnimatedInputText(inputText: CharSequence?) {
        this.inputText = inputText

        inputTextIndex = 0

        text = ""

        animationHandler.removeCallbacks(characterAdder)
        animationHandler.postDelayed(characterAdder, animationDelay)
    }

    fun setCharacterAnimationDelay(delay: Long) {
        animationDelay = delay
    }
}

