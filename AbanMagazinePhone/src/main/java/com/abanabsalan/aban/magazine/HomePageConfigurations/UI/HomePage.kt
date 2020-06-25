/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/25/20 12:23 PM
 * Last modified 6/25/20 12:20 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.PostView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.nio.charset.Charset

class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {

            val link = URL("https://abanabsalan.com/wp-json/wp/v2/posts?page=2&per_page=1&orderby=date&order=asc")
            val downloadedData = link.readText(Charset.defaultCharset())

            val fullJsonData: JSONArray = JSONArray(downloadedData)

            val rawPostContent = JSONObject(fullJsonData[0].toString()).getJSONObject("content").get("rendered").toString()

            startActivity(Intent(applicationContext, PostView::class.java).putExtra("RawPostContent", rawPostContent))

        }
    }
}