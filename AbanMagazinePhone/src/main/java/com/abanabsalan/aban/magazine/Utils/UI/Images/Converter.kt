/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/26/20 7:05 PM
 * Last modified 6/26/20 6:24 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.UI.Images

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.VectorDrawable

fun drawableToBitmap(drawable: Drawable): Bitmap {


    return when (drawable) {
        is VectorDrawable -> {

            val vectorDrawable = drawable

            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
            vectorDrawable.draw(canvas)

            bitmap

        }
        is BitmapDrawable -> {
            val bitmapDrawable = drawable

            bitmapDrawable.bitmap

        }
        is LayerDrawable -> {

            val layerDrawable = drawable

            val bitmap = Bitmap.createBitmap(
                layerDrawable.intrinsicWidth,
                layerDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            layerDrawable.setBounds(0, 0, canvas.width, canvas.height)
            layerDrawable.draw(canvas)

            bitmap

        }
        else -> {

            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)

            bitmap
        }
    }
}