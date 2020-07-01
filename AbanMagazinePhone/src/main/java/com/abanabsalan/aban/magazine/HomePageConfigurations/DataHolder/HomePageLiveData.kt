/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/30/20 4:21 PM
 * Last modified 6/30/20 4:21 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import org.json.JSONArray

class HomePageLiveData : ViewModel() {

    val postsLiveItemDataSingle: MutableLiveData<ArrayList<PostsItemData>> by lazy {
        MutableLiveData<ArrayList<PostsItemData>>()
    }

    fun prepareRawDataToRenderForPosts(postsJsonArray: JSONArray) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

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
}