/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/5/20 3:47 PM
 * Last modified 7/5/20 3:39 PM
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

    val singleSinglePostLiveItemData: MutableLiveData<ArrayList<SinglePostItemData>> by lazy {
        MutableLiveData<ArrayList<SinglePostItemData>>()
    }

    fun prepareRawDataToRenderForSinglePost(rawPostContent: String) = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {

        val singlePostItemsData: ArrayList<SinglePostItemData> = ArrayList<SinglePostItemData>()

        val postContent: Document = Jsoup.parse(rawPostContent)

        postContent.allElements.forEachIndexed { index, element ->

            if (element.`is`("p")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Paragraph ${element}")

                singlePostItemsData.add(
                    SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostParagraph,
                        PostItemParagraph(element.text()),
                        null,
                        null,
                        null
                    )
                )

            } else if (element.`is`("a")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Link ${element}")

                singlePostItemsData.add(
                    SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostTextLink,
                        null,
                        null,
                        PostItemTextLink("${element}"),
                        null
                    )
                )

            } else if (element.`is`("img")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Image ${element.attr("src").replace(" ", "")}")

                singlePostItemsData.add(
                    SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostImage,
                        null,
                        PostItemImage(element.attr("src").replace(" ", "")),
                        null,
                        null
                    )
                )

            } else if (element.`is`("iframe")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "iFrame ${element}")

                singlePostItemsData.add(
                    SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostIFrame,
                        null,
                        null,
                        null,
                        PostItemIFrame("${element}")
                    )
                )

            }

        }

        singleSinglePostLiveItemData.postValue(singlePostItemsData)

    }
}