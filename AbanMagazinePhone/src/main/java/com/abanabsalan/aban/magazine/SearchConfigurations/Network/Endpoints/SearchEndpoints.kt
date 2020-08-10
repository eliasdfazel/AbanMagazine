/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 12:49 AM
 * Last modified 8/10/20 12:18 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

class SearchEndpoints (private val searchQuery: String) {

    val getSearchEndpointsAddress: String = "${GeneralEndpoints.GeneralEndpointsAddress}/wp-json/wp/v2/search?search=" +
            "${searchQuery}"

}