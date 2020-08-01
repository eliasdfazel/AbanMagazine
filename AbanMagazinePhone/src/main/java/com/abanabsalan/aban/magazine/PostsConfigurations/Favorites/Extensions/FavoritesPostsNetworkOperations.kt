/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 9:42 PM
 * Last modified 7/31/20 9:32 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Extensions

import android.util.Log
import android.widget.Toast
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Network.Operations.FavoritesPostsRetrieval
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.Favorites.Utils.FavoriteIt
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import org.json.JSONArray

fun FavoritesPostsView.favoritesPostsNetworkOperations() {

    val favoriteIt: FavoriteIt = FavoriteIt(applicationContext)

    val favoritesPostsRetrieval: FavoritesPostsRetrieval = FavoritesPostsRetrieval(applicationContext)

    val allFavoritedPosts = favoriteIt.getAllFavoritedPosts()

    if (!allFavoritedPosts.isNullOrEmpty()) {

        favoritesPostsRetrieval.start(
            allFavoritedPosts,
            object : JsonRequestResponseInterface {

                override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                    super.jsonRequestResponseSuccessHandler(rawDataJsonArray)


                    favoritesPostsLiveData.prepareRawDataToRenderForAllFavoritedPosts(rawDataJsonArray)

                }

                override fun jsonRequestResponseFailureHandler(jsonError: String?) {
                    Log.d(this@favoritesPostsNetworkOperations.javaClass.simpleName, jsonError.toString())

                }

            })

    } else {

        Toast.makeText(applicationContext, getString(R.string.noMoreContent), Toast.LENGTH_LONG).show()

    }

}