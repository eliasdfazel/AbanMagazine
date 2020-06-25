/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/25/20 12:23 PM
 * Last modified 6/25/20 12:02 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.databinding.PostsViewUiBinding
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PostView : AppCompatActivity() {

    lateinit var postsViewUiBinding: PostsViewUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewUiBinding = PostsViewUiBinding.inflate(layoutInflater)
        setContentView(postsViewUiBinding.root)

        val rawPostContent = intent.getStringExtra("RawPostContent")
        val postContent: Document = Jsoup.parse(rawPostContent)

        postContent.allElements.forEachIndexed { index, element ->

            if (element.`is`("p")) {
                Log.d(this@PostView.javaClass.simpleName, "Paragraph ${element}")


            } else if (element.`is`("a")) {
                Log.d(this@PostView.javaClass.simpleName, "Link ${element}")


            } else if (element.`is`("img")) {
                Log.d(this@PostView.javaClass.simpleName, "Image ${element}")


            } else if (element.`is`("iframe")) {
                Log.d(this@PostView.javaClass.simpleName, "iFrame ${element}")


            }

        }
    }
}