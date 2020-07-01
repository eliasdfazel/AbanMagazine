/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/1/20 2:52 PM
 * Last modified 7/1/20 2:52 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints

import com.abanabsalan.aban.magazine.Utils.Network.GeneralEndpoints

data class SubCategoriesEndpointsFactory (
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

class SubCategoriesEndpoints (subCategoriesEndpointsFactory: SubCategoriesEndpointsFactory = SubCategoriesEndpointsFactory(parentCategoryId = 2016)) {

    val getSubCategoriesEndpointsAddress: String = "${GeneralEndpoints.GeneralEndpointsAddress}/wp-json/wp/v2/categories?" +
            "parent=${subCategoriesEndpointsFactory.parentCategoryId}&per_page=${subCategoriesEndpointsFactory.amountOfCategoriesToGet}&orderby=${subCategoriesEndpointsFactory.sortByType}"
}