/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/25/20 9:41 AM
 * Last modified 9/25/20 9:03 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder

object ProductShowcase {
    const val ProductShowcase: String = "ProductShowcase"
    const val ProductLink: String = "ProductLink"
    const val ProductTitle: String = "ProductTitle"
    const val ProductDescription: String = "ProductDescription"
    const val ProductBrand: String = "ProductBrand"
    const val ProductImage: String = "ProductImage"
}

data class ProductShowcaseItemData(var titleOfProduct: String,
                                   var linkToImageProduct: String,
                                   var linkToProduct: String,
                                   var descriptionOfProduct: String?,
                                   var brandOfProduct: String?)