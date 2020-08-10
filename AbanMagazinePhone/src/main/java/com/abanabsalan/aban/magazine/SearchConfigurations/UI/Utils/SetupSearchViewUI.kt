/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 2:16 AM
 * Last modified 8/10/20 2:14 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI.Utils

import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.hidePopupSearches
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.SearchConfigurations.Network.Operations.SearchResultsRetrieval
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.abanabsalan.aban.magazine.Utils.UI.Display.navigationBarHeight
import com.abanabsalan.aban.magazine.databinding.SearchPopupUiViewBinding
import org.json.JSONArray
import javax.net.ssl.HttpsURLConnection

class SetupSearchViewUI (private val context: HomePage, private val searchPopupUiViewBinding: SearchPopupUiViewBinding) {

    init {

        val searchPopupUiViewParams = searchPopupUiViewBinding.textInputSearchView.layoutParams as ConstraintLayout.LayoutParams
        searchPopupUiViewParams.bottomMargin = searchPopupUiViewParams.bottomMargin + navigationBarHeight(context)
        searchPopupUiViewBinding.textInputSearchView.layoutParams = searchPopupUiViewParams

        searchPopupUiViewBinding.root.setOnClickListener {

            context.hidePopupSearches()

        }

        searchPopupUiViewBinding.searchViewAction.setOnClickListener {

            SearchResultsRetrieval(context, "${searchPopupUiViewBinding.searchView.text}")
                .start(object : JsonRequestResponseInterface {

                    override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                        super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                        //start new activity and pass string of json array then parse string to jsonArray in SearchResults activity

                    }

                    override fun jsonRequestResponseFailureHandler(networkError: Int?) {
                        super.jsonRequestResponseFailureHandler(networkError)

                        Log.d("Specific Category Retrieval", networkError.toString())

                        when (networkError) {
                            HttpsURLConnection.HTTP_BAD_REQUEST -> {/*400*/



                            }
                        }

                    }

                })

        }

        searchPopupUiViewBinding.searchView.setOnEditorActionListener { textView, actionId, keyEvent ->

            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {

                    SearchResultsRetrieval(context, "${textView.text}")
                        .start(object : JsonRequestResponseInterface {

                            override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                                super.jsonRequestResponseSuccessHandler(rawDataJsonArray)



                            }

                            override fun jsonRequestResponseFailureHandler(networkError: Int?) {
                                super.jsonRequestResponseFailureHandler(networkError)



                            }

                        })

                }
            }

            true
        }

    }

}