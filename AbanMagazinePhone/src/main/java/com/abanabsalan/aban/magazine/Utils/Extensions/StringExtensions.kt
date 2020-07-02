/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/2/20 2:04 PM
 * Last modified 7/2/20 1:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Extensions

fun String.convertUnicodeToText() : String{

    var splitInputString: String = this@convertUnicodeToText.split(" ")[0]
    splitInputString = splitInputString.replace("\\", "")

    val arrayStrings = splitInputString.split("u".toRegex()).toTypedArray()

    var finalText: String = this@convertUnicodeToText
    for (i in 1 until arrayStrings.size) {
        val hexVal = arrayStrings[i].toInt(16)
        finalText += hexVal.toChar()
    }

    return finalText
}