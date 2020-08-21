/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/21/20 3:05 AM
 * Last modified 8/21/20 2:49 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Data

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