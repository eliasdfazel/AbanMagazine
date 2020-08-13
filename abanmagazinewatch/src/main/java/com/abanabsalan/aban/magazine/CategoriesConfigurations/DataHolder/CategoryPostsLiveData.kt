/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/6/20 3:33 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class CategoryPostsLiveData : ViewModel() {

    val allCategoryPosts: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    fun prepareRawDataToRenderForAllCategoryPosts(rawDataJsonArray: JSONArray) = CoroutineScope(Dispatchers.IO).launch {

        val specificCategoryPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until rawDataJsonArray.length()) {
            val postJsonObject = rawDataJsonArray.getJSONObject(i)
            Log.d("${this@CategoryPostsLiveData.javaClass.simpleName} PrepareRawDataToRenderForAllCategoryPosts", postJsonObject.getString(
                PostsDataParameters.JsonDataStructure.PostId))

            specificCategoryPostsItemData.add(PostsItemData(
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

        allCategoryPosts.postValue(specificCategoryPostsItemData)

    }
}