/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 8:14 AM
 * Last modified 7/31/20 8:04 AM
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
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL
import java.nio.charset.Charset

class PostsLiveData : ViewModel() {

    val singleSinglePostLiveItemData: MutableLiveData<ArrayList<SinglePostItemData>> by lazy {
        MutableLiveData<ArrayList<SinglePostItemData>>()
    }

    val allFavoritedPosts: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    val toggleTheme: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun prepareRawDataToRenderForSinglePost(rawPostContent: String) = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {

        val singlePostItemsData: ArrayList<SinglePostItemData> = ArrayList<SinglePostItemData>()

        val postContent: Document = Jsoup.parse(rawPostContent)

        postContent.select("blockquote").empty()

        val allHtmlElement = postContent.allElements

        allHtmlElement.forEachIndexed { index, element ->

            if (element.`is`("blockquote")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Block Quote ${element}")

                try {

                    if (element.select("blockquote").attr("class").contains(PostsDataParameters.PostItemsBlockQuoteType.BlockQuoteInstagram)) {

                        val instagramEmbeddedId: String = element.select("blockquote")
                            .attr("data-instgrm-permalink")
                            .replace("/?utm_source=ig_embed&utm_campaign=loading", "")

                        val instagramEmbeddedRequestLink: String = "https://api.instagram.com/oembed/?url=${instagramEmbeddedId}"

                        val instagramPostData = URL(instagramEmbeddedRequestLink).readText(Charset.defaultCharset())
                        val rawJsonInstagramPost = JSONObject(instagramPostData)

                        singlePostItemsData.add(
                            SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostBlockQuoteInstagram,
                                null,
                                null,
                                null,
                                null,
                                null,
                                PostItemBlockQuoteInstagram(
                                    instagramUsername = rawJsonInstagramPost.getString("author_name"),
                                    instagramUserAddress = rawJsonInstagramPost.getString("author_url"),
                                    instagramPostAddress = instagramEmbeddedId,
                                    instagramPostImage = rawJsonInstagramPost.getString("thumbnail_url"),
                                    instagramPostTitle = rawJsonInstagramPost.getString("title")
                                )
                            )
                        )

                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else if (element.`is`("p")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Paragraph ${element}")

                singlePostItemsData.add(
                    SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostParagraph,
                        PostItemParagraph(element.text()),
                        null,
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
                        null,
                        null
                    )
                )

            } else if (element.`is`("img")) {
                Log.d(this@PostsLiveData.javaClass.simpleName, "Image ${element.attr("src").replace(" ", "")}")

                val targetLink: String? = try {
                    element.parent().select("a").first().attr("abs:href")
                } catch (e: Exception) {
                    e.printStackTrace()

                    null
                }

                singlePostItemsData.add(
                    SinglePostItemData(PostsDataParameters.PostItemsViewParameters.PostImage,
                        null,
                        null,
                        PostItemImage(element.attr("src").replace(" ", ""), targetLink),
                        null,
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
                        PostItemIFrame("${element}"),
                        null
                    )
                )

            }

        }

        singleSinglePostLiveItemData.postValue(singlePostItemsData)

    }
}