/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 2:15 AM
 * Last modified 7/24/20 12:54 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints.PostsEndpoints
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints.PostsEndpointsFactory
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.json.JSONArray

object EnqueueEndPointQuery {
    const val JSON_REQUEST_TIMEOUT = (1000 * 3)
    const val JSON_REQUEST_RETRIES = (3)
}

class NewestPostsRetrieval (private val context: Context) {

    fun start(postsEndpointsFactory: PostsEndpointsFactory,
              jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).async {

        val postsEndpoints: PostsEndpoints = PostsEndpoints(postsEndpointsFactory)

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            postsEndpoints.getPostEndpointsAddress,
            null,
            Response.Listener<JSONArray?> { response ->
                Log.d("JsonObjectRequest ${this@NewestPostsRetrieval.javaClass.simpleName}", response.toString())

                if (response != null) {

                    jsonRequestResponseInterface.jsonRequestResponseSuccessHandler(response)

                }

            }, Response.ErrorListener {
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