/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/23/20 12:43 PM
 * Last modified 6/23/20 12:43 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.databinding.EntryConfigurationViewBinding
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.nio.charset.Charset


class EntryConfiguration : AppCompatActivity() {

    private lateinit var entryConfigurationViewBinding: EntryConfigurationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryConfigurationViewBinding = EntryConfigurationViewBinding.inflate(layoutInflater)
        setContentView(entryConfigurationViewBinding.root)

        Thread(Runnable {

            val link = URL("https://abanabsalan.com/wp-json/wp/v2/posts?page=2&per_page=1&orderby=date&order=asc")
            val downloadedData = link.readText(Charset.defaultCharset())

            val fullJsonData: JSONArray = JSONArray(downloadedData)

            val content = JSONObject(fullJsonData[0].toString()).getJSONObject("content").get("rendered").toString()

            runOnUiThread {
                entryConfigurationViewBinding.text.settings.loadWithOverviewMode = true
                entryConfigurationViewBinding.text.settings.loadsImagesAutomatically = true
                entryConfigurationViewBinding.text.settings.textZoom = 200



                entryConfigurationViewBinding.text.settings.useWideViewPort = true
                entryConfigurationViewBinding.text.loadData("<html dir=\"rtl\" lang=\"\"><body>" + content + "</body></html>","text/html", "UTF-8")
            }

        }).start()

    }
}