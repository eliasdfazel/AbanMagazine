/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 7:38 AM
 * Last modified 7/31/20 7:38 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints

class FavoritedPostsEndpoints {

    var favoritedPostBaseLink: StringBuilder = StringBuilder("https://abanabsalan.com/wp-json/wp/v2/posts?include[]=")

    fun getFavoritedPostsEndpoints(listOfFavoritedPostIds: MutableMap<String, *>) : String {

        listOfFavoritedPostIds.forEach { key, value ->

            favoritedPostBaseLink.append("${key}")
        }

        return favoritedPostBaseLink.toString()
    }

}