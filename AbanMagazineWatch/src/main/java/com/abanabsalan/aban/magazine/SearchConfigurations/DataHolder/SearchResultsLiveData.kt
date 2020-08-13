/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.DataHolder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.URL
import java.nio.charset.Charset

class SearchResultsLiveData : ViewModel() {

    val allSearchResultsPosts: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    fun getSearchResultPostsEndpoints(listOfPostIds: MutableMap<String, *>) : String {

        val searchResultsPostBaseLink: StringBuilder = StringBuilder("https://abanabsalan.com/wp-json/wp/v2/posts?")

        listOfPostIds.forEach { key, value ->

            searchResultsPostBaseLink.append("&include[]=${key}")

        }

        return searchResultsPostBaseLink.toString()
    }

    fun retrieveAllDataOfSearchResults(searchResultEndpoints: String) = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {

        val allPostsSearchResults = URL(searchResultEndpoints).readText(Charset.defaultCharset())

        val allSearchResultsJsonArray = JSONArray(allPostsSearchResults)

        val searchResultsPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until allSearchResultsJsonArray.length()) {
            val postJsonObject = allSearchResultsJsonArray.getJSONObject(i)
            Log.d("${this@SearchResultsLiveData.javaClass.simpleName} RetrieveAllDataOfSearchResults", postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            searchResultsPostsItemData.add(PostsItemData(
                postLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostLink),
                postId = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId),
                postFeaturedImage = postJsonObject.getString(PostsDataParameters.JsonDataStructure.FeauturedImage),
                postTitle = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostTitle).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postContent = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostContent).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postExcerpt = postJsonObject.getJSONObject(PostsDataParameters.JsonDataStructure.PostExcerpt).getString(PostsDataParameters.JsonDataStructure.Rendered),
                postPublishDate = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostDate),
                postCategories = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostCategories).join(","),
                postTags = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.PostTags).join(","),
                relatedPostsContent = postJsonObject.getJSONArray(PostsDataParameters.JsonDataStructure.RelatedPosts).toString()
            ))

        }

        allSearchResultsPosts.postValue(searchResultsPostsItemData)

    }

}