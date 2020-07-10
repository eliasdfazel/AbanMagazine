/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/10/20 1:10 PM
 * Last modified 7/10/20 1:04 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Endpoints.CategoriesEndpointsFactory
import com.abanabsalan.aban.magazine.CategoriesConfigurations.Network.Operations.CategoriesRetrieval
import com.abanabsalan.aban.magazine.HomePageConfigurations.DataHolder.HomePageLiveData
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Endpoints.PostsEndpointsFactory
import com.abanabsalan.aban.magazine.PostsConfigurations.Network.Operations.NewestPostsRetrieval
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Endpoints.SpecificCategoryEndpointsFactory
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Network.Operations.SpecificCategoryRetrieval
import com.abanabsalan.aban.magazine.SpecificCategoryConfigurations.Utils.PageCounter
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.abanabsalan.aban.magazine.Utils.Network.NetworkSettingCallback
import com.abanabsalan.aban.magazine.Utils.UI.NotifyUser.SnackbarActionHandlerInterface
import com.abanabsalan.aban.magazine.Utils.UI.NotifyUser.SnackbarBuilder
import com.abanabsalan.aban.magazine.databinding.HomePageViewBinding
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import javax.net.ssl.HttpsURLConnection

fun HomePage.startNetworkOperations() {

    if (networkCheckpoint.networkConnection()) {

        startSpecificCategoryRetrieval(applicationContext, homePageViewBinding, homePageLiveData, PageCounter.PageNumberToLoad)

        val newestPostsRetrieval: NewestPostsRetrieval = NewestPostsRetrieval(applicationContext)
        newestPostsRetrieval.start(
            PostsEndpointsFactory(
                numberOfPageInPostsList = 1,
                amountOfPostsToGet = 6,
                sortByType = "date",
                sortBy = "desc"
            ),
            object : JsonRequestResponseInterface {

                override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                    super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                    homePageLiveData.prepareRawDataToRenderForNewestPosts(rawDataJsonArray)

                }

                override fun jsonRequestResponseFailureHandler(jsonError: String?) {
                    Log.d(this@startNetworkOperations.javaClass.simpleName, jsonError.toString())

                }

            })

        val categoriesRetrieval: CategoriesRetrieval = CategoriesRetrieval(applicationContext)
        categoriesRetrieval.start(
            CategoriesEndpointsFactory(
                excludeCategory = "199,1034,150",
                amountOfCategoriesToGet = 100,
                sortByType = "count"
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
            messageText= getString(R.string.noInternetConnectionText),
            messageDuration = Snackbar.LENGTH_INDEFINITE,
            actionButtonText = R.string.turnOnText,
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

fun startSpecificCategoryRetrieval(context: Context, homePageViewBinding: HomePageViewBinding, homePageLiveData: HomePageLiveData, numberOfPageInPostsList: Int) {

    homePageViewBinding.featuredPostsLoadingView.visibility = View.VISIBLE

    val specificCategoryRetrieval: SpecificCategoryRetrieval = SpecificCategoryRetrieval(context)
    specificCategoryRetrieval.start(
        SpecificCategoryEndpointsFactory(
            numberOfPageInPostsList,
            sortByType = "id",
            IdOfCategoryToGetPosts = 150 // Featured Posts
        ),
        object : JsonRequestResponseInterface {

            override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                homePageLiveData.prepareRawDataToRenderForSpecificPosts(rawDataJsonArray)

            }

            override fun jsonRequestResponseFailureHandler(networkError: Int?) {
                Log.d("Specific Category Retrieval", networkError.toString())

                when (networkError) {
                    HttpsURLConnection.HTTP_BAD_REQUEST -> {/*400*/

                        homePageLiveData.specificCategoryLiveItemData.postValue(null)

                    }
                }

            }

        })

}