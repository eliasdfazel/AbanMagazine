/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 3:54 PM
 * Last modified 6/28/20 3:54 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PostsLiveData : ViewModel() {

    val postLiveItemData: MutableLiveData<ArrayList<PostItemData>> by lazy {
        MutableLiveData<ArrayList<PostItemData>>()
    }

    fun prepareRawDataToRender(rawPostContent: String) = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {

        val postItemsData: ArrayList<PostItemData> = ArrayList<PostItemData>()

        val postContent: Document = Jsoup.parse(rawPostContent)

        postContent.allElements.forEachIndexed { index, element ->

            if (element.`is`("p")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Paragraph ${element}")

                postItemsData.add(
                    PostItemData(PostsDataParameters.PostItemsParameters.PostParagraph,
                        PostItemParagraph(element.text()),
                        null,
                        null,
                        null
                    )
                )

            } else if (element.`is`("a")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Link ${element}")



            } else if (element.`is`("img")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Image ${element.attr("src")}")

                postItemsData.add(
                    PostItemData(PostsDataParameters.PostItemsParameters.PostImage,
                        null,
                        PostItemImage(element.attr("src")),
                        null,
                        null
                    )
                )

            } else if (element.`is`("iframe")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "iFrame ${element}")


            }

        }

        postLiveItemData.postValue(postItemsData)

    }
}