/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.DataHolder

object SearchResultJsonStructure {
    const val SearchResultPostId: String = "id"
}

data class SearchResultsDataParameters (var searchResultPostId: String)