/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/18/20 11:03 AM
 * Last modified 7/18/20 11:01 AM
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
                        null,
                        null
                    )
                )

            } else if (element.`is`("h4")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "SubTitle Headline 4 ${element}")

                singlePostItemsData.add(
                    SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostSubTitle,
                        null,
                        PostItemSubTitle(element.text()),
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
                        null,
                        PostItemIFrame("${element}")
                    )
                )

            }

        }

        singleSinglePostLiveItemData.postValue(singlePostItemsData)

    }
}