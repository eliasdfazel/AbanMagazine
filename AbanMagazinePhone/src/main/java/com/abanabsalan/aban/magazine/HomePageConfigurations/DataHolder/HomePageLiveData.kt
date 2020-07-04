/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/4/20 11:25 AM
 * Last modified 7/4/20 11:24 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesItemData
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import org.json.JSONArray

class HomePageLiveData : ViewModel() {

    val newestPostsLiveItemData: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    val categoriesLiveItemData: MutableLiveData<ArrayList<CategoriesItemData>> by lazy {
        MutableLiveData<ArrayList<CategoriesItemData>>()
    }

    val specificCategoryLiveItemData: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    fun prepareRawDataToRenderForNewestPosts(postsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        val postsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()


        for (i in 0 until postsJsonArray.length()) {
            val postJsonObject = postsJsonArray.getJSONObject(i)
            Log.d(this@HomePageLiveData.javaClass.simpleName, postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            /*
            *
            *
            *
            */

        }
    }

    fun prepareRawDataToRenderForCategories(categoriesJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        val categoriesItemData: ArrayList<CategoriesItemData> = ArrayList<CategoriesItemData>()

        for (i in 0 until categoriesJsonArray.length()) {
            val categoryJsonObject = categoriesJsonArray.getJSONObject(i)
            Log.d(this@HomePageLiveData.javaClass.simpleName, categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryId))

            if (categoryJsonObject.getInt(CategoriesDataParameters.JsonDataStructure.CategoryParentId) == 0) {

                categoriesItemData.add(CategoriesItemData(
                    categoryLink = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryLink),
                    categoryId = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryId),
                    categoryName = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryName),
                    categoryDescription = categoryJsonObject.getString(CategoriesDataParameters.JsonDataStructure.CategoryDescription)
                ))

            }

        }

        categoriesLiveItemData.postValue(categoriesItemData)

    }

    fun prepareRawDataToRenderForSpecificPosts(featuredPostsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        val newestPostsItemData: ArrayList<PostsItemData> = ArrayList<PostsItemData>()

        for (i in 0 until featuredPostsJsonArray.length()) {
            val postJsonObject = featuredPostsJsonArray.getJSONObject(i)
            Log.d(this@HomePageLiveData.javaClass.simpleName, postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId))

            newestPostsItemData.add(PostsItemData(
                postLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostLink),
                postId = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostId),
                postFeaturedImage = postJsonObject.getString(PostsDataParameters.JsonDataStructure.FeauturedImage),
                postTitle = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostTitle),
                postContent = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostContent),
                postExcerpt = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostExcerpt),
                postCommentsLink = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostCommentsLink),
                postPublishDate = postJsonObject.getString(PostsDataParameters.JsonDataStructure.PostDate)
            ))

        }

        specificCategoryLiveItemData.postValue(newestPostsItemData)

    }

}