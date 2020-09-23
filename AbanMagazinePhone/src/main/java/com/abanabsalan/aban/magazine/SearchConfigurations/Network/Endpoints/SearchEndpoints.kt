/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/23/20 4:20 AM
 * Last modified 9/23/20 4:19 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class SearchEndpointsFactory (
    /**
     * Base Domain Address. In Case You Have Several Wordpress Websites.
     **/
    var baseDomainEndpoint: String = GeneralEndpoints.GeneralEndpointsAddressDotCom,
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

    val getSearchEndpointsAddress: String = "${searchEndpointsFactory.baseDomainEndpoint}/wp-json/wp/v2/search?" +
            "page=${searchEndpointsFactory.numberOfPageInPostsList}&" +
            "per_page=${searchEndpointsFactory.amountOfPostsToGet}&" +
            "orderby=${searchEndpointsFactory.sortByType}&" +
            "order=${searchEndpointsFactory.sortBy}&" +
            "search=${searchEndpointsFactory.searchQuery}"

}