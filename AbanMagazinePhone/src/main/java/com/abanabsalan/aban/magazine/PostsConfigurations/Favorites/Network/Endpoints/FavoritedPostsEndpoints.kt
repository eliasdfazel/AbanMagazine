/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 11:57 PM
 * Last modified 7/31/20 11:52 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Network.Endpoints

class FavoritedPostsEndpoints {

    var favoritedPostBaseLink: StringBuilder = StringBuilder("https://abanabsalan.com/wp-json/wp/v2/posts?")

    fun getFavoritedPostsEndpoints(listOfFavoritedPostIds: MutableMap<String, *>) : String {

        listOfFavoritedPostIds.forEach { key, value ->

            favoritedPostBaseLink.append("&include[]=${key}")

        }

        return favoritedPostBaseLink.toString()
    }

}