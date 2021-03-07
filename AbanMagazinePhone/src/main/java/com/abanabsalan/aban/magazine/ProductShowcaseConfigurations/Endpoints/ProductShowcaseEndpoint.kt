/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 3/7/21 7:16 AM
 * Last modified 3/7/21 7:16 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Endpoints

class ProductShowcaseEndpoint {

    private object Security {
        const val ConsumerKey = "ck_e4cd587ec0d07dbac1138b06602e8e028a99ad43"
        const val ConsumerSecret = "cs_55a87f5e6354700dc5fc3861d3fae730df9c4921"
    }
    /**
     * Add All Your Products Title, Image, Link To Product In A Wordpress Page Inside
     * -
     * Then Replace This Link.
     **/
    val getAllProductsShowcaseEndpoint = "https://abanabsalan.com/wp-json/wc/v3/products?" +
            "consumer_key=${Security.ConsumerKey}" +
            "&" +
            "consumer_secret=${Security.ConsumerSecret}"

    fun getProductsSearchEndpoint(productSearchQuery: String): String = "$getAllProductsShowcaseEndpoint&search=$productSearchQuery"

}