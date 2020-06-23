/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/23/20 11:26 AM
 * Last modified 6/23/20 11:23 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Network.Endpoints

data class PostsEndpointsFactory (
    /**
     * Number Of Page
     **/
    var numberOfPageInPosts: Int = 1,
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

class PostsEndpoints (postsEndpointsFactory: PostsEndpointsFactory? = PostsEndpointsFactory()) {

    companion object {
        private const val PostEndpointsAddress = "https://abanabsalan.com/wp-json/wp/v2/posts?"
    }
}