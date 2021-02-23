/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/23/21 10:27 AM
 * Last modified 2/23/21 10:26 AM
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
    const val ProductImages: String = "ProductImages"
    const val ProductPrice: String = "ProductPrice"
}

data class ProductShowcaseItemData(var titleOfProduct: String,
                                   var linkToImageProduct: String,
                                   var linkToProduct: String,
                                   var descriptionOfProduct: String?,
                                   var brandOfProduct: String?)