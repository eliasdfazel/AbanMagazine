/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 8/10/20 5:08 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class SearchEndpointsFactory (
    /**
     * Number Of Page In List Of All Posts
     **/
    var numberOfPageInPostsList: Int = 1,
    /**
     * Amount Of Posts Per Each Page
     **/
    var amountOfPostsToGet: Int = 10,
    /**
     * Sort collection by object attribute
     **/
    var sortByType: String = "date",
    /**
     * Order sort attribute ascending or descending
     **/
    var sortBy: String = "desc",
    var searchQuery: String
)

class SearchEndpoints (private val searchEndpointsFactory: SearchEndpointsFactory) {

    val getSearchEndpointsAddress: String = "${GeneralEndpoints.GeneralEndpointsAddress}/wp-json/wp/v2/search?" +
            "page=${searchEndpointsFactory.numberOfPageInPostsList}&" +
            "per_page=${searchEndpointsFactory.amountOfPostsToGet}&" +
            "orderby=${searchEndpointsFactory.sortByType}&" +
            "order=${searchEndpointsFactory.sortBy}&" +
            "search=${searchEndpointsFactory.searchQuery}"

}