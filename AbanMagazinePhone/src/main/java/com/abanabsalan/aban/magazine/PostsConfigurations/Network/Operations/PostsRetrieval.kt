/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/30/20 3:37 PM
 * Last modified 6/30/20 3:37 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints.PostsEndpoints
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints.PostsEndpointsFactory
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations.Extensions.JsonRequestResponseInterface
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

object EnqueueEndPointQuery {
    const val JSON_REQUEST_TIMEOUT = (1000 * 3)
    const val JSON_REQUEST_RETRIES = (3)
}

class PostsRetrieval (private val context: Context) {

    fun start(postsEndpointsFactory: PostsEndpointsFactory,
              jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).launch {

        val postsEndpoints: PostsEndpoints = PostsEndpoints(postsEndpointsFactory)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            postsEndpoints.getPostEndpointsAddress,
            null,
            Response.Listener<JSONObject?> { response ->
                Log.d("JsonObjectRequest", response?.getJSONObject(PostsDataParameters.JsonDataStructure.PostId).toString())

                if (response != null) {

                    jsonRequestResponseInterface.jsonRequestResponseSuccessHandler(response)
                }

            }, Response.ErrorListener {
                Log.d("JsonObjectRequestError", it.toString())

                jsonRequestResponseInterface.jsonRequestResponseFailureHandler(it.toString())
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