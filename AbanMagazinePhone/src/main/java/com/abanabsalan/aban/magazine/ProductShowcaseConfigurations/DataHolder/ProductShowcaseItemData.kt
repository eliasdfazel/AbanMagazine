/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/4/20 6:41 AM
 * Last modified 9/4/20 6:35 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder

object ProductShowcase {
    const val ProductLink: String = "ProductLink"
    const val ProductImage: String = "ProductImage"
}

data class ProductShowcaseItemData(var nameOfProduct: String, var linkToImageProduct: String, var linkToProduct: String)