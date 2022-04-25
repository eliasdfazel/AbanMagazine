/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class OnlineStoreLiveData : ViewModel() {

    val allProductsShowcaseLiveItemData: MutableLiveData<ArrayList<ProductJsonDataStructureItem>> by lazy {
        MutableLiveData<ArrayList<ProductJsonDataStructureItem>>()
    }

    fun prepareRawDataToRenderForAllProductsShowcase(rawProductShowcase: JSONArray) {

        val productShowcaseItemData: ArrayList<ProductJsonDataStructureItem> = ArrayList<ProductJsonDataStructureItem>()

        for (i in 0 until rawProductShowcase.length()) {

            val aProductJsonObject = rawProductShowcase[i] as JSONObject

            productShowcaseItemData.add(ProductJsonDataStructureItem(
                productId = aProductJsonObject.getString(ProductJsonDataStructure.ProductId),
                productName = aProductJsonObject.getString(ProductJsonDataStructure.ProductName),
                productFeaturedImage = (aProductJsonObject.getJSONArray(ProductJsonDataStructure.ProductImages)[0] as JSONObject).getString(ProductJsonDataStructure.ProductFeaturedImage),
                productImages = aProductJsonObject.getJSONArray(ProductJsonDataStructure.ProductImages),
                productDescription = aProductJsonObject.getString(ProductJsonDataStructure.ProductDescription),
                productShortDescription = aProductJsonObject.getString(ProductJsonDataStructure.ProductShortDescription),
                productPrice = aProductJsonObject.getString(ProductJsonDataStructure.ProductPrice),
                productOnSale = aProductJsonObject.getBoolean(ProductJsonDataStructure.ProductOnSale),
                productSalePrice = aProductJsonObject.getString(ProductJsonDataStructure.ProductSalePrice),
                productLink = aProductJsonObject.getString(ProductJsonDataStructure.ProductLink),
                productCategories = aProductJsonObject.getJSONArray(ProductJsonDataStructure.ProductCategories),
                productTags = aProductJsonObject.getJSONArray(ProductJsonDataStructure.ProductTags),
                productRelated = aProductJsonObject.getJSONArray(ProductJsonDataStructure.ProductRelated)
            ))

            Log.d(this@OnlineStoreLiveData.javaClass.simpleName, "Product: ${aProductJsonObject.getString(ProductJsonDataStructure.ProductName)}")
        }

        allProductsShowcaseLiveItemData.postValue(productShowcaseItemData)

    }

}