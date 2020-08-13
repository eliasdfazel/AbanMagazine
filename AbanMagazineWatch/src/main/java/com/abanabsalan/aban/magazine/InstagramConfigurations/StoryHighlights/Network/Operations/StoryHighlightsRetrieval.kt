/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.InstagramConfigurations.StoryHighlights.Network.Endpoints.StoryHighlightsEndpoint
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.json.JSONObject

object EnqueueEndPointQuery {
    const val JSON_REQUEST_TIMEOUT = (1000 * 3)
    const val JSON_REQUEST_RETRIES = (3)
}
class StoryHighlightsRetrieval (private val context: Context) {

    fun start(jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).async {

        val storyHighlightsEndpoint: StoryHighlightsEndpoint = StoryHighlightsEndpoint()

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            storyHighlightsEndpoint.getInstagramStoryHighlightsEndpoint,
            null,
            Response.Listener<JSONObject?> { response ->
                Log.d("JsonObjectRequest ${this@StoryHighlightsRetrieval.javaClass.simpleName}", response.toString())

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