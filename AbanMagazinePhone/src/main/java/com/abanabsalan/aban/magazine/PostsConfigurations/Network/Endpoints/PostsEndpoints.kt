/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/23/20 4:20 AM
 * Last modified 9/23/20 4:18 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class PostsEndpointsFactory (
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
     * Language Based On Tags Of Language Id. For Example, A Tag Of English With Id of 1234
     **/
    var postsLanguage: String = PostsDataParameters.Language.English
)

class PostsEndpoints (postsEndpointsFactory: PostsEndpointsFactory) {

    val getPostEndpointsAddress: String = "${postsEndpointsFactory.baseDomainEndpoint}/wp-json/wp/v2/posts?" +
            "page=${postsEndpointsFactory.numberOfPageInPostsList}&per_page=${postsEndpointsFactory.amountOfPostsToGet}&orderby=${postsEndpointsFactory.sortByType}&order=${postsEndpointsFactory.sortBy}" +
            "&tags[]=${postsEndpointsFactory.postsLanguage}"

}