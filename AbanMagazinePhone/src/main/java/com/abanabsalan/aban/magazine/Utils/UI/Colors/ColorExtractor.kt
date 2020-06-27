/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/27/20 12:45 PM
 * Last modified 6/27/20 12:45 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Colors

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.UI.Images.drawableToBitmap


fun extractVibrantColor(context: Context, drawable: Drawable): Int {

    var vibrantColor: Int = context.getColor(R.color.default_color_game)

    val bitmap: Bitmap = drawableToBitmap(drawable)

    var currentColor: Palette
    try {
        if (bitmap != null && !bitmap.isRecycled) {
            currentColor = Palette.from(bitmap).generate()
            val defaultColor: Int = context.getColor(R.color.default_color_game)
            vibrantColor = currentColor.getVibrantColor(defaultColor)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        try {
            if (bitmap != null && !bitmap.isRecycled) {
                currentColor = Palette.from(bitmap).generate()
                val defaultColor: Int = context.getColor(R.color.default_color_game)
                vibrantColor = currentColor.getMutedColor(defaultColor)
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }
    return vibrantColor
}

fun extractDominantColor(context: Context, drawable: Drawable): Int {

    var dominantColor: Int = context.getColor(R.color.default_color)

    val bitmap: Bitmap = drawableToBitmap(drawable)

    var currentColor: Palette
    try {
        if (bitmap != null && !bitmap.isRecycled) {
            currentColor = Palette.from(bitmap).generate()
            val defaultColor: Int = context.getColor(R.color.default_color)
            dominantColor = currentColor.getDominantColor(defaultColor)
        }
    } catch (e: Exception) {
        e.printStackTrace()

        try {
            if (bitmap != null && !bitmap.isRecycled) {
                currentColor = Palette.from(bitmap).generate()
                val defaultColor: Int = context.getColor(R.color.default_color)
                dominantColor = currentColor.getMutedColor(defaultColor)
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }
    return dominantColor
}

fun isDrawableLightDark(context: Context, drawable: Drawable): Boolean {

    var isLightDark = false

    val calculateLuminance = ColorUtils.calculateLuminance(extractDominantColor(context, drawable))

    if (calculateLuminance > 0.50) { //light
        isLightDark = true
    } else if (calculateLuminance <= 0.50) { //dark
        isLightDark = false
    }

    return isLightDark
}