/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/4/20 11:25 AM
 * Last modified 7/4/20 11:07 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Endpoints.SpecificCategoryEndpoints
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Endpoints.SpecificCategoryEndpointsFactory
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

object EnqueueEndPointQuery {
    const val JSON_REQUEST_TIMEOUT = (1000 * 3)
    const val JSON_REQUEST_RETRIES = (3)
}

class SpecificCategoryRetrieval (private val context: Context){

    fun start(specificCategoryEndpointsFactory: SpecificCategoryEndpointsFactory,
              jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).launch {

        val specificCategoryEndpoints: SpecificCategoryEndpoints = SpecificCategoryEndpoints(specificCategoryEndpointsFactory)

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            specificCategoryEndpoints.getSpecificCategoryPostsEndpointAddress,
            null,
            Response.Listener<JSONArray?> { response ->
                Log.d("JsonObjectRequest", response.toString())

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