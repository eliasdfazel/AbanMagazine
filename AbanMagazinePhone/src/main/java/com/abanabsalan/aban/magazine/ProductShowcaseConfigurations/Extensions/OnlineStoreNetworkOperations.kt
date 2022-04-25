/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions

import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.OnlineStore
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import org.json.JSONArray

fun OnlineStore.startOnlineStoreNetworkOperations() {

    productShowcaseRetrieval.getAllProducts(object : JsonRequestResponseInterface {

        override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
            super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

            onlineStoreLiveData.prepareRawDataToRenderForAllProductsShowcase(rawDataJsonArray)

        }

        override fun jsonRequestResponseFailureHandler(jsonError: String?) {
            super.jsonRequestResponseFailureHandler(jsonError)

        }

        override fun jsonRequestResponseFailureHandler(networkError: Int?) {
            super.jsonRequestResponseFailureHandler(networkError)

        }

    })

}