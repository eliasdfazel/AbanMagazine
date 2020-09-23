/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/23/20 4:20 AM
 * Last modified 9/23/20 4:19 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class SubCategoriesEndpointsFactory (
    /**
     * Base Domain Address. In Case You Have Several Wordpress Websites.
     **/
    var baseDomainEndpoint: String = GeneralEndpoints.GeneralEndpointsAddressDotCom,
    /**
     * Always Change This To A Parent Category Id You Want
     **/
    val parentCategoryId: Int,
    /**
     * Amount Of Categories Per Each Page - Default is 100
     **/
    var amountOfCategoriesToGet: Int = 100,
    /**
     * Sort collection by object attribute
     **/
    var sortByType: String = "count"
)

class SubCategoriesEndpoints (subCategoriesEndpointsFactory: SubCategoriesEndpointsFactory) {

    val getSubCategoriesEndpointsAddress: String = "${subCategoriesEndpointsFactory.baseDomainEndpoint}/wp-json/wp/v2/categories?" +
            "parent=${subCategoriesEndpointsFactory.parentCategoryId}&per_page=${subCategoriesEndpointsFactory.amountOfCategoriesToGet}&orderby=${subCategoriesEndpointsFactory.sortByType}"
}