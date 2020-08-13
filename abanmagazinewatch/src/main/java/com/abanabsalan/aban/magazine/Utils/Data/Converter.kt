/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 7/26/20 5:58 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Data

import android.util.Base64

fun encodeByteArrayToString(inputByteArray: ByteArray): String {

    return Base64.encodeToString(inputByteArray, Base64.DEFAULT)
}

fun decodeStringToByteArray(inputByteArrayString: String) : ByteArray {

    return Base64.decode(inputByteArrayString, Base64.DEFAULT)
}