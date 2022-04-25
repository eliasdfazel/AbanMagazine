/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Extensions

import android.util.Log
import android.widget.Toast
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Network.Operations.FavoritesPostsRetrieval
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.UI.FavoritesPostsView
import com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Utils.FavoriteIt
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

        this@favoritesPostsNetworkOperations.finish()

    }

}