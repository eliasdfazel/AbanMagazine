/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/10/20 5:08 AM
 * Last modified 8/10/20 4:51 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.SearchConfigurations.UI.Utils

import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.abanabsalan.aban.magazine.HomePageConfigurations.Extensions.hidePopupSearches
import com.abanabsalan.aban.magazine.HomePageConfigurations.UI.HomePage
import com.abanabsalan.aban.magazine.R
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

            searchPopupUiViewBinding.textInputSearchView.isErrorEnabled = false

            SearchResultsRetrieval(context, "${searchPopupUiViewBinding.searchView.text}")
                .start(object : JsonRequestResponseInterface {

                    override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                        super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                        Intent().apply {
                            putExtra(Intent.EXTRA_TEXT, rawDataJsonArray.toString())

                            context.startActivity(this@apply, ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, 0).toBundle())
                        }

                    }

                    override fun jsonRequestResponseFailureHandler(networkError: Int?) {
                        super.jsonRequestResponseFailureHandler(networkError)

                        Log.d("Specific Category Retrieval", networkError.toString())

                        when (networkError) {
                            HttpsURLConnection.HTTP_BAD_REQUEST -> {/*400*/

                                searchPopupUiViewBinding.textInputSearchView.isErrorEnabled = true

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

                                Intent().apply {
                                    putExtra(Intent.EXTRA_TEXT, rawDataJsonArray.toString())

                                    context.startActivity(this@apply, ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, 0).toBundle())
                                }

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