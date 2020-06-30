/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/30/20 3:36 PM
 * Last modified 6/30/20 3:03 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class PostsEndpointsFactory (
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
    var sortBy: String = "desc"
)

class PostsEndpoints (postsEndpointsFactory: PostsEndpointsFactory = PostsEndpointsFactory()) {

    val getPostEndpointsAddress: String = "${GeneralEndpoints.GeneralEndpointsAddress}/wp-json/wp/v2/posts?" +
            "page=${postsEndpointsFactory.numberOfPageInPostsList}&per_page=${postsEndpointsFactory.amountOfPostsToGet}&orderby=${postsEndpointsFactory.sortByType}&order=${postsEndpointsFactory.sortBy}"

}