/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/27/21 7:25 AM
 * Last modified 2/27/21 4:19 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Operations

import android.content.Context
import android.util.Log
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Endpoints.ProductShowcaseEndpoint
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
class ProductShowcaseRetrieval (private val context: Context) {

    val productShowcaseEndpoint: ProductShowcaseEndpoint = ProductShowcaseEndpoint()

    fun getAllProducts(jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).async {

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            productShowcaseEndpoint.getAllProductsShowcaseEndpoint,
            null,
            { response ->
                Log.d("JsonArrayRequest ${this@ProductShowcaseRetrieval.javaClass.simpleName}", response.toString())

                if (response != null) {

                    jsonRequestResponseInterface.jsonRequestResponseSuccessHandler(response)

                }

            }, {
                Log.d("JsonArrayRequestError", it?.networkResponse?.statusCode.toString())

                jsonRequestResponseInterface.jsonRequestResponseFailureHandler(it?.networkResponse?.statusCode)

            })

        jsonArrayRequest.retryPolicy = DefaultRetryPolicy(
            EnqueueEndPointQuery.JSON_REQUEST_TIMEOUT,
            EnqueueEndPointQuery.JSON_REQUEST_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        jsonArrayRequest.setShouldCache(false)

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonArrayRequest)

    }

    fun startProductsSearch(productSearchQuery: String, jsonRequestResponseInterface: JsonRequestResponseInterface) = CoroutineScope(Dispatchers.IO).async {

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            productShowcaseEndpoint.getProductsSearchEndpoint(productSearchQuery),
            null,
            { response ->
                Log.d("JsonArrayRequest ${this@ProductShowcaseRetrieval.javaClass.simpleName}", response.toString())

                if (response != null) {

                    jsonRequestResponseInterface.jsonRequestResponseSuccessHandler(response)

                }

            }, {
                Log.d("JsonArrayRequestError", it?.networkResponse?.statusCode.toString())

                jsonRequestResponseInterface.jsonRequestResponseFailureHandler(it?.networkResponse?.statusCode)

            })

        jsonArrayRequest.retryPolicy = DefaultRetryPolicy(
            EnqueueEndPointQuery.JSON_REQUEST_TIMEOUT,
            EnqueueEndPointQuery.JSON_REQUEST_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        jsonArrayRequest.setShouldCache(false)

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonArrayRequest)

    }

}