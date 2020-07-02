/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/2/20 2:04 PM
 * Last modified 7/2/20 2:03 PM
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

        const val CategoryHasParent: String = "parent"
    }

    object CategoryParameters {

    }

    object CategoryItemsViewParameters {

    }

}

data class CategoriesItemData (var categoryLink: String,
                          var categoryId: String,
                          var categoryName: String,
                          var categoryDescription: String,
                          var categoryCommentsLink: String?)