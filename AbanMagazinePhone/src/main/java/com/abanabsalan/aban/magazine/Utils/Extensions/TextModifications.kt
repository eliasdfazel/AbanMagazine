/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Extensions

fun String.capitalizeFirstChar(): String {

    if (this@capitalizeFirstChar.isEmpty()) {
        return ""
    }

    val first = this@capitalizeFirstChar[0]

    return if (Character.isUpperCase(first)) {

        this@capitalizeFirstChar

    } else {

        Character.toUpperCase(first).toString() + this@capitalizeFirstChar.substring(1)
    }

}