/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/26/20 7:05 PM
 * Last modified 6/26/20 6:33 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Display

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue

fun DpToPixel(context:Context, dp: Float): Float {
    val resources: Resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun PixelToDp(context:Context, px: Float): Float {
    val resources: Resources = context.resources
    val metrics = resources.displayMetrics
    return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun DpToInteger(context:Context, dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}

fun displayX(context:Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun displayY(context:Context): Int {
    return context.resources.displayMetrics.heightPixels
}