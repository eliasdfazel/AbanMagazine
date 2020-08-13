/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder

class CategoriesDataParameters {

    object JsonDataStructure {
        const val CategoryLink: String = "link"
        const val CategoryId: String = "id"

        const val CategoryName: String = "name"

        const val CategoryPostCount: String = "count"
        const val CategoryDescription: String = "description"

        const val CategoryParentId: String = "parent"
    }

    object CategoryParameters {
        const val CategoryLink: String = "CategoryLinkAddress"
        const val CategoryId: String = "CategoryId"

        const val CategoryName: String = "CategoryName"

        const val CategoryPostCount: String = "CategoryPostCount"
        const val CategoryDescription: String = "CategoryDescription"

        const val CategoryParentId: String = "CategoryParentId"

        const val CategoryFeaturedImageBaseLink: String = "https://bit.ly/AbanAbsalan"
    }

    object CategoryItemsViewParameters {

    }

}

data class CategoriesItemData (var categoryLink: String,
                          var categoryId: String,
                          var categoryName: String,
                          var categoryDescription: String)