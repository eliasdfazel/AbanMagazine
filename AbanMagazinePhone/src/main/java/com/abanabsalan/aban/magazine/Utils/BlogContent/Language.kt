/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 3:54 PM
 * Last modified 6/28/20 2:52 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.BlogContent


class Language {

    fun checkRtl(aString: String): Boolean {

        return if (aString.isNotBlank() && aString.isNotEmpty()) {
            val firstChar = aString[0]
            firstChar.toInt() in 0x590..0x6ff
        } else {
             false
        }
    }
}