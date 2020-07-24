/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/24/20 12:54 AM
 * Last modified 7/24/20 12:32 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints.CategoriesEndpoints
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints.CategoriesEndpointsFactory
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

class CategoriesRetrieval (private val context: Context){

    fun start(categoriesEndpointsFactory: CategoriesEndpointsFactory,
              jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).async {

        val categoriesEndpoints: CategoriesEndpoints = CategoriesEndpoints(categoriesEndpointsFactory)

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            categoriesEndpoints.getCategoriesEndpointsAddress,
            null,
            Response.Listener<JSONArray?> { response ->
                Log.d("JsonObjectRequest ${this@CategoriesRetrieval.javaClass.simpleName}", response.toString())

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