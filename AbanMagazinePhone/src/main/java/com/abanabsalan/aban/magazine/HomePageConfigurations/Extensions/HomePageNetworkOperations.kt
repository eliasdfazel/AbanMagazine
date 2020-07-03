/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/2/20 4:39 PM
 * Last modified 7/2/20 4:38 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints.CategoriesEndpointsFactory
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Operations.CategoriesRetrieval
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints.PostsEndpointsFactory
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations.PostsRetrieval
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.abanabsalan.aban.magazine.Utils.Network.NetworkSettingCallback
import com.abanabsalan.aban.magazine.Utils.UI.NotifyUser.SnackbarActionHandlerInterface
import com.abanabsalan.aban.magazine.Utils.UI.NotifyUser.SnackbarBuilder
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray

fun HomePage.startNetworkOperations() {

    if (networkCheckpoint.networkConnection()) {

        val postsRetrieval = PostsRetrieval(applicationContext)

        postsRetrieval.start(
            PostsEndpointsFactory(
                numberOfPageInPostsList = 1,
                amountOfPostsToGet = 3,
                sortByType = "date",
                sortBy = "desc"
            ),
            object : JsonRequestResponseInterface {

                override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                    super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                    homePageLiveData.prepareRawDataToRenderForPosts(rawDataJsonArray)

                }

                override fun jsonRequestResponseFailureHandler(jsonError: String?) {
                    Log.d(this@startNetworkOperations.javaClass.simpleName, jsonError.toString())

                }

            })

        val categoriesRetrieval: CategoriesRetrieval = CategoriesRetrieval(applicationContext)

        categoriesRetrieval.start(
            CategoriesEndpointsFactory(
                excludeCategory = 199,
                amountOfCategoriesToGet = 100,
                sortByType = "count",
                IdOfCategoryToGetPosts = 0
            ),

            object : JsonRequestResponseInterface {

                override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                    super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                    homePageLiveData.prepareRawDataToRenderForCategories(rawDataJsonArray)

                }

                override fun jsonRequestResponseFailureHandler(jsonError: String?) {
                    Log.d(this@startNetworkOperations.javaClass.simpleName, jsonError.toString())

                }

            })


    } else {
        Log.d(this@startNetworkOperations.javaClass.simpleName, "No Network Connection")

        SnackbarBuilder(applicationContext).show (
            rootView = homePageViewBinding.rootView,
            messageText= getString(R.string.noInternetConnection),
            messageDuration = Snackbar.LENGTH_INDEFINITE,
            actionButtonText = R.string.turnOn,
            snackbarActionHandlerInterface = object : SnackbarActionHandlerInterface {

                override fun onActionButtonClicked(snackbar: Snackbar) {
                    super.onActionButtonClicked(snackbar)

                    if (!networkCheckpoint.networkConnection()) {

                        startActivityForResult(
                            Intent(Settings.ACTION_WIFI_SETTINGS)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                            NetworkSettingCallback.WifiSetting
                        )

                    } else {

                        snackbar.dismiss()

                    }

                }

            }
        )

    }

}
