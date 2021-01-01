/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 1/1/21 6:35 AM
 * Last modified 1/1/21 6:30 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.CacheConfigurations.CacheMechanism
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints.CategoriesEndpoints
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints.CategoriesEndpointsFactory
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

object EnqueueEndPointQuery {
    const val JSON_REQUEST_TIMEOUT = (1000 * 3)
    const val JSON_REQUEST_RETRIES = (3)
}

class CategoriesRetrieval (private val context: Context){

    private val cacheMechanism = CacheMechanism(context)

    fun start(categoriesEndpointsFactory: CategoriesEndpointsFactory,
              jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).async {

        println(">>>>>>>>>>>> 00")

        val categoriesEndpoints: CategoriesEndpoints = CategoriesEndpoints(categoriesEndpointsFactory)

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            categoriesEndpoints.getCategoriesEndpointsAddress,
            null,
            { response ->
                Log.d("JsonObjectRequest ${this@CategoriesRetrieval.javaClass.simpleName}", response.toString())

                if (response != null) {

                    jsonRequestResponseInterface.jsonRequestResponseSuccessHandler(response)

                }

            }, {
                Log.d("JsonObjectRequestError", it?.networkResponse?.statusCode.toString())

                jsonRequestResponseInterface.jsonRequestResponseFailureHandler(it?.networkResponse?.statusCode)

            })

        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            EnqueueEndPointQuery.JSON_REQUEST_TIMEOUT,
            EnqueueEndPointQuery.JSON_REQUEST_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        val requestQueue = Volley.newRequestQueue(context)

        if (cacheMechanism.checkTimeToLive()) {

            println(">>>>>>>>>>>> 11")

            cacheMechanism.storeCachedTime()

            requestQueue.cache.clear()

        }

        println(">>>>>>>>>>>> 22")

        requestQueue.add(jsonObjectRequest)
        requestQueue.start()

    }
}