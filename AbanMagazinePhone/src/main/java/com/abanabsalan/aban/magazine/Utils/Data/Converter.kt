/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 1/1/21 5:24 AM
 * Last modified 12/31/20 1:28 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Data

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream


fun encodeByteArrayToString(inputByteArray: ByteArray): String {

    return Base64.encodeToString(inputByteArray, Base64.DEFAULT)
}

fun decodeStringToByteArray(inputByteArrayString: String) : ByteArray {

    return Base64.decode(inputByteArrayString, Base64.DEFAULT)
}

fun Drawable.convertDrawableToByteArray() : ByteArray {

    val bitmap: Bitmap = (this@convertDrawableToByteArray as BitmapDrawable).getBitmap()

    val byteArrayOutputStream = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

    return byteArrayOutputStream.toByteArray()
}