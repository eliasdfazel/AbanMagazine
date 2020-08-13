/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 2:15 AM
 * Last modified 7/24/20 12:54 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.BlogContent


class Language {

    /**
     * False For RTL Language
     **/
    fun checkRtl(aString: String): Boolean {

        return if (aString.isNotBlank() && aString.isNotEmpty()) {
            val firstChar = aString[0]
            firstChar.toInt() in 0x590..0x6ff
        } else {
             false
        }
    }
}