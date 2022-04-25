/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
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

    fun jsonRequestResponseFailureHandler(jsonError: String?) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, jsonError.toString())

    }

    fun jsonRequestResponseFailureHandler(networkError: Int?) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, networkError.toString())

    }
}