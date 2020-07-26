/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/25/20 7:35 PM
 * Last modified 7/25/20 7:11 PM
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
import java.io.ByteArrayOutputStream

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

fun drawableToByteArray(drawable: Drawable) : ByteArray{

    val byteArrayOutputStream = ByteArrayOutputStream()

    val bitmap = (drawable as BitmapDrawable).bitmap
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

    return byteArrayOutputStream.toByteArray()
}