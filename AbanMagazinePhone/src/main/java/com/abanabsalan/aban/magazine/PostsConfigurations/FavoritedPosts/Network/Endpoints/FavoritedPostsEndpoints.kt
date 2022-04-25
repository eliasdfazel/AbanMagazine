/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Network.Endpoints

class FavoritedPostsEndpoints {

    var favoritedPostBaseLink: StringBuilder = StringBuilder("https://abanabsalan.com/wp-json/wp/v2/posts?")

    fun getFavoritedPostsEndpoints(listOfFavoritedPostIds: MutableMap<String, *>) : String {

        listOfFavoritedPostIds.forEach { key, value ->

            favoritedPostBaseLink.append("&include[]=${key}")

        }

        return favoritedPostBaseLink.toString()
    }

}