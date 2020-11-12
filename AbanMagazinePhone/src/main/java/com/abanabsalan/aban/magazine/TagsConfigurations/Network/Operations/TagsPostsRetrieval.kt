/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 6:05 AM
 * Last modified 11/12/20 4:31 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.TagsConfigurations.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.TagsConfigurations.Network.Endpoints.TagsEndpoints
import com.abanabsalan.aban.magazine.TagsConfigurations.Network.Endpoints.TagsEndpointsFactory
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

class TagsPostsRetrieval (private val context: Context){

    fun start(tagsEndpointsFactory: TagsEndpointsFactory,
              jsonRequestResponseInterface: JsonRequestResponseInterface
    ) = CoroutineScope(Dispatchers.IO).async {

        val tagsEndpoints: TagsEndpoints = TagsEndpoints(tagsEndpointsFactory)

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            tagsEndpoints.generateTagsEndpoint(),
            null,
            { response ->
                Log.d("JsonObjectRequest ${this@TagsPostsRetrieval.javaClass.simpleName}", response.toString())

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
        requestQueue.add(jsonObjectRequest)
    }
}