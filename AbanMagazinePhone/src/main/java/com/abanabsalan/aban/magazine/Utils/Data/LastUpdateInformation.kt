/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Data

import android.content.Context
import com.abanabsalan.aban.magazine.BuildConfig

class LastUpdateInformation (private val context: Context){

    fun isApplicationUpdated() : Boolean{

        val fileIO: FileIO = FileIO(context)

        return (BuildConfig.VERSION_CODE > fileIO.readFile(".Updated")?.toInt()?:0)
    }
}