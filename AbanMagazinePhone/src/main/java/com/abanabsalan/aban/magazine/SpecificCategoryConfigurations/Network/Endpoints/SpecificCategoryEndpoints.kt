/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.PostsConfigurations.DataHolder.PostsDataParameters
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Utils.PageCounter
import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class SpecificCategoryEndpointsFactory (
    /**
     * Base Domain Address. In Case You Have Several Wordpress Websites.
     **/
    var baseDomainEndpoint: String = GeneralEndpoints.GeneralEndpointsAddressDotCom,
    /**
     * Number Of Page In List Of All Posts
     **/
    var numberOfPageInPostsList: Int = PageCounter.PageNumberToLoad,
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

    var IdOfCategoryToGetPosts: Int,
    /**
     * Language Based On Tags Of Language Id. For Example, A Tag Of English With Id of 1234
     **/
    var postsLanguage: String = PostsDataParameters.Language.Persian
)

class SpecificCategoryEndpoints (specificCategoryEndpointsFactory: SpecificCategoryEndpointsFactory) {

    /**
     * Always Change This To Id Category You Want.
     * Get All Posts Of A Specific Category.
     * Then Use Posts Json Parameters To Get Each Post Data.
     **/
    val getSpecificCategoryPostsEndpointAddress: String = "${specificCategoryEndpointsFactory.baseDomainEndpoint}/wp-json/wp/v2/posts?" +
            "categories=${specificCategoryEndpointsFactory.IdOfCategoryToGetPosts?:1}&page=${specificCategoryEndpointsFactory.numberOfPageInPostsList}&per_page=${specificCategoryEndpointsFactory.amountOfPostsToGet}&orderby=${specificCategoryEndpointsFactory.sortByType}&order=${specificCategoryEndpointsFactory.sortBy}" +
            "&tags[]=${specificCategoryEndpointsFactory.postsLanguage}"

}