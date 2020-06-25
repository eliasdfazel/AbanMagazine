/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/25/20 2:26 PM
 * Last modified 6/25/20 2:14 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.UI

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemImage
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostItemParagraph
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.UI.Adapters.PostViewAdapter
import com.abanabsalan.aban.magazine.databinding.PostsViewUiBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PostView : AppCompatActivity() {

    lateinit var postsViewUiBinding: PostsViewUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewUiBinding = PostsViewUiBinding.inflate(layoutInflater)
        setContentView(postsViewUiBinding.root)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        postsViewUiBinding.postRecyclerView.layoutManager = linearLayoutManager

        val rawPostContent = intent.getStringExtra("RawPostContent")

        val postViewAdapter: PostViewAdapter = PostViewAdapter(applicationContext)

        CoroutineScope(Dispatchers.Default).launch {

            val postContent: Document = Jsoup.parse(rawPostContent)

            postContent.allElements.forEachIndexed { index, element ->

                if (element.`is`("p")) {
                    Log.d(this@PostView.javaClass.simpleName, "Paragraph ${element}")

                    postViewAdapter.postItemsData.add(
                        PostItemData(PostsDataParameters.PostItemsParameters.PostParagraph,
                            PostItemParagraph(element.text()),
                            null,
                            null,
                            null
                        )
                    )

                } else if (element.`is`("a")) {
                    Log.d(this@PostView.javaClass.simpleName, "Link ${element}")



                } else if (element.`is`("img")) {
                    Log.d(this@PostView.javaClass.simpleName, "Image ${element.attr("src")}")

                    postViewAdapter.postItemsData.add(
                        PostItemData(PostsDataParameters.PostItemsParameters.PostImage,
                            null,
                            PostItemImage(element.attr("src")),
                            null,
                            null
                        )
                    )

                } else if (element.`is`("iframe")) {
                    Log.d(this@PostView.javaClass.simpleName, "iFrame ${element}")


                }

            }

            withContext(Dispatchers.Main) {

                postsViewUiBinding.postRecyclerView.adapter = postViewAdapter
            }
        }

    }
}