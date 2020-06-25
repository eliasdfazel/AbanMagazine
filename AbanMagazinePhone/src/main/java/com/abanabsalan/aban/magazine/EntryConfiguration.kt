/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/24/20 5:36 PM
 * Last modified 6/24/20 5:36 PM
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
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
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

            val document: Document = Jsoup.parse(content)


            document.allElements.forEachIndexed { index, element ->

                println(">>> >>> > ${element.cssSelector()}")

                if (element.`is`("p")) {

                    println(">>>> Paragraph " + element)

                } else if (element.`is`("a")) {

                    println(">>>> Link " + element)

                } else if (element.`is`("img")) {

                    println(">>>> Image " + element)

                }

            }

//            val paragraph: Elements = document.select("p")
//
//            paragraph.forEachIndexed { index, element ->
//
//                val e = element.text()
//
//                println(">>> ${index}. " + e)
//            }

//            runOnUiThread {
//                entryConfigurationViewBinding.text.settings.javaScriptEnabled = true
//                entryConfigurationViewBinding.text.settings.loadWithOverviewMode = true
//                entryConfigurationViewBinding.text.settings.loadsImagesAutomatically = true
//                entryConfigurationViewBinding.text.settings.textZoom = 200

                /*
                * <iframe width="560" height="315" src="https://www.youtube.com/embed/k1_Ojp8LY_o" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                * */
//                entryConfigurationViewBinding.text.settings.useWideViewPort = true
//                entryConfigurationViewBinding.text.loadData(
//                    "<iframe width=\"560\" height=\"315\" src=\"https://www.aparat.com/video/video/embed/videohash/Xgf1S/vt/frame\"></iframe>",
//                    "text/html", "UTF-8")
//            }


        }).start()

    }
}