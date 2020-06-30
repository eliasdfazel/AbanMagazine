/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/30/20 3:36 PM
 * Last modified 6/30/20 3:33 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations.Extensions

import org.json.JSONArray
import org.json.JSONObject

interface JsonRequestResponseInterface {
    fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {}
    fun jsonRequestResponseSuccessHandler(rawDataJsonObject: JSONObject) {}
    fun jsonRequestResponseFailureHandler(jsonError: String?)
}