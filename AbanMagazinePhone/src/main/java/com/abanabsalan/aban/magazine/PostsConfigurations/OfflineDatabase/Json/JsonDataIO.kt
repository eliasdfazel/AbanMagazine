/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.Json

import com.google.gson.Gson

class JsonDataIO {

    val gson: Gson = JsonConfiguration().initialize()

    fun writeParagraphToJson(jsonDataType: String, jsonDataContent: String) {



    }

    fun writeSubTitleToJson(jsonDataType: String, jsonDataContent: String) {



    }

    fun writeTextLinkToJson(jsonDataType: String, jsonDataContent: String) {



    }

    fun writeImageByteArrayToJson(jsonDataType: String, jsonDataContent: ByteArray) {



    }

}