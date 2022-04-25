/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 5:58 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder

class SubCategoriesDataParameters {

    object JsonDataStructure {
        const val SubCategoryLink: String = "link"
        const val SubCategoryId: String = "id"

        const val SubCategoryName: String = "name"

        const val SubCategoryPostCount: String = "count"
        const val SubCategoryDescription: String = "description"

        const val SubCategoryParentId: String = "parent"
    }

    object SubCategoryParameters {
        const val SubCategoryLink: String = "SubCategoryLinkAddress"
        const val SubCategoryId: String = "SubCategoryId"

        const val SubCategoryName: String = "SubCategoryName"

        const val SubCategoryPostCount: String = "SubCategoryPostCount"
        const val SubCategoryDescription: String = "SubCategoryDescription"

        const val SubCategoryParentId: String = "SubCategoryParentId"
    }

    object SubCategoryItemsViewParameters {

    }

}


data class SubCategoriesItemData (var subCategoryLink: String,
                               var subCategoryId: String,
                               var subCategoryName: String,
                               var subCategoryDescription: String,
                               var subCategoryCommentsLink: String?)