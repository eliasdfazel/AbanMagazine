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

class SubCategoriesDataParameters {

    object JsonDataStructure {

    }

    object SubCategoryParameters {

    }

    object SubCategoryItemsViewParameters {

    }

}


data class SubCategoriesItemData (var subCategoryLink: String,
                               var subCategoryId: String,
                               var subCategoryName: String,
                               var subCategoryDescription: String,
                               var subCategoryCommentsLink: String?)