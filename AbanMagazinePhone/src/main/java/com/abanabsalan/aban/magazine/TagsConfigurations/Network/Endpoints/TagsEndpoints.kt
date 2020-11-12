/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 6:05 AM
 * Last modified 11/12/20 6:05 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.TagsConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class TagsEndpointsFactory (
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
    var amountOfPostsToGet: Int = 3,
    /**
     * Sort collection by object attribute
     **/
    var sortByType: String = "date",
    /**
     * Order sort attribute ascending or descending
     **/
    var sortBy: String = "desc",
    /**
     * Set Tags Ids as CSV
     **/
    var tags: String
)

class TagsEndpoints (private val tagsEndpointsFactory: TagsEndpointsFactory) {

    fun generateTagsEndpoint() : String {

        val tagsEndpointsAddress: StringBuilder = StringBuilder("${tagsEndpointsFactory.baseDomainEndpoint}/wp-json/wp/v2/posts?" +
                "page=${tagsEndpointsFactory.numberOfPageInPostsList}&per_page=${tagsEndpointsFactory.amountOfPostsToGet}&orderby=${tagsEndpointsFactory.sortByType}&order=${tagsEndpointsFactory.sortBy}")

        tagsEndpointsFactory.tags.split(",").forEach { tag ->

            tagsEndpointsAddress.append("&tags[]=${tag}")

        }

        return tagsEndpointsAddress.toString()
    }
}