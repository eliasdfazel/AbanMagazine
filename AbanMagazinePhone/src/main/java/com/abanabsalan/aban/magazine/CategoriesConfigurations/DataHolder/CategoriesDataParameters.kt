/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/1/20 3:54 PM
 * Last modified 7/1/20 3:44 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder

class CategoriesDataParameters {

    object JsonDataStructure {

    }

    object CategoryParameters {

    }

    object CategoryItemsParameters {

    }

}

data class CategoriesItemData (var categoryLink: String,
                          var categoryId: String,
                          var categoryName: String,
                          var postDescription: String,
                          var postCommentsLink: String,
                          var postPublishDate: String)