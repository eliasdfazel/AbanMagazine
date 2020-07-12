/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/12/20 12:18 PM
 * Last modified 7/12/20 10:55 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoryPostsLiveData
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Endpoints.SpecificCategoryEndpointsFactory
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Operations.SpecificCategoryRetrieval
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import org.json.JSONArray
import javax.net.ssl.HttpsURLConnection

class AllCategoryPostsRetrieval (private val context: Context){

    fun start(categoryPostsLiveData: CategoryPostsLiveData, categoryId: Int) {

        val specificCategoryRetrieval: SpecificCategoryRetrieval = SpecificCategoryRetrieval(context)
        specificCategoryRetrieval.start(
            SpecificCategoryEndpointsFactory(
                numberOfPageInPostsList = 1,
                sortByType = "id",
                IdOfCategoryToGetPosts = categoryId,
                amountOfPostsToGet = 7
            ),
            object : JsonRequestResponseInterface {

                override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                    super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                    categoryPostsLiveData.prepareRawDataToRenderForAllCategoryPosts(rawDataJsonArray)

                }

                override fun jsonRequestResponseFailureHandler(networkError: Int?) {
                    Log.d("Specific Category Retrieval", networkError.toString())

                    when (networkError) {
                        HttpsURLConnection.HTTP_BAD_REQUEST -> {/*400*/

                            categoryPostsLiveData.allCategoryPosts.postValue(null)

                        }
                    }

                }

            })

    }
}