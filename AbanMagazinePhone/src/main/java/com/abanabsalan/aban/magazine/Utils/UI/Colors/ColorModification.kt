/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/26/20 7:05 PM
 * Last modified 6/26/20 6:31 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Colors

import android.graphics.Color
import kotlin.math.roundToInt

fun setColorAlpha(color: Int, alphaValue: Float /*1 -- 255*/): Int {

    val alpha = (Color.alpha(color) * alphaValue).roundToInt()

    val red = Color.red(color)
    val green = Color.green(color)
    val blue = Color.blue(color)

    return Color.argb(alpha, red, green, blue)
}