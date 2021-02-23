/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/23/21 10:27 AM
 * Last modified 2/23/21 10:22 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Endpoints

class ProductShowcaseEndpoint {

    private object Security {
        const val ConsumerKey = "ck_6f3a98c1af6d7f8c704c65274604cd63b0d2b6d4"
        const val ConsumerSecret = "cs_9ff6ce36823e88669359bb5e441c543be0c7fb5b"
    }
    /**
     * Add All Your Products Title, Image, Link To Product In A Wordpress Page Inside
     * -
     * Then Replace This Link.
     **/
    val getProductShowcaseEndpoint = "https://abanabsalan.com/wp-json/wc/v3/products?" +
            "consumer_key=${Security.ConsumerKey}" +
            "&" +
            "consumer_secret=${Security.ConsumerSecret}"

}