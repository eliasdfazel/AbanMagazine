/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Extensions

import android.view.View
import android.view.inputmethod.EditorInfo
import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.UI.OnlineStore
import com.abanabsalan.aban.magazine.R
import com.abanabsalan.aban.magazine.Utils.Network.Extensions.JsonRequestResponseInterface
import com.abanabsalan.aban.magazine.Utils.System.hideKeyboard
import com.abanabsalan.aban.magazine.Utils.System.showKeyboard
import com.airbnb.lottie.LottieDrawable
import org.json.JSONArray

fun OnlineStore.setupOnlineStoreActionListener() {

    onlineStoreLayoutBinding.searchProductsView.setOnClickListener {

        onlineStoreLayoutBinding.searchProductsView.setImageDrawable(null)

        onlineStoreLayoutBinding.textInputSearchView.visibility = View.VISIBLE
        onlineStoreLayoutBinding.textInputSearchView.bringToFront()

        onlineStoreLayoutBinding.searchView.requestFocus()
        showKeyboard(applicationContext, onlineStoreLayoutBinding.searchView)

    }


    onlineStoreLayoutBinding.searchView.setOnEditorActionListener { textView, actionId, keyEvent ->

        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {

                if (textView.text.isNullOrBlank()) {



                } else {

                    onlineStoreLayoutBinding.searchProductsView.setAnimation(R.raw.product_search_animation).also {
                        onlineStoreLayoutBinding.searchProductsView.repeatCount = LottieDrawable.INFINITE
                    }
                    onlineStoreLayoutBinding.searchProductsView.playAnimation()

                    productShowcaseRetrieval.startProductsSearch(textView.text.toString(), object :
                        JsonRequestResponseInterface {

                        override fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
                            super.jsonRequestResponseSuccessHandler(rawDataJsonArray)

                            onlineStoreLayoutBinding.searchProductsView.pauseAnimation()
                            onlineStoreLayoutBinding.searchProductsView.setImageDrawable(null)

                            onlineStoreLayoutBinding.searchView.clearFocus()
                            hideKeyboard(applicationContext, onlineStoreLayoutBinding.searchView)

                            onlineStoreLiveData.prepareRawDataToRenderForAllProductsShowcase(rawDataJsonArray)

                        }

                        override fun jsonRequestResponseFailureHandler(jsonError: String?) {
                            super.jsonRequestResponseFailureHandler(jsonError)

                        }

                        override fun jsonRequestResponseFailureHandler(networkError: Int?) {
                            super.jsonRequestResponseFailureHandler(networkError)

                        }

                    })

                }

            }
        }

        true
    }

}