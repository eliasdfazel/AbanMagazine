/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/2/20 2:41 PM
 * Last modified 7/2/20 2:28 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.Network.Extensions

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

interface JsonRequestResponseInterface {
    fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, rawDataJsonArray.toString())
    }

    fun jsonRequestResponseSuccessHandler(rawDataJsonObject: JSONObject) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, rawDataJsonObject.toString())

    }

    fun jsonRequestResponseFailureHandler(jsonError: String?)
}