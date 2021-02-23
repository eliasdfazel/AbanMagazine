/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/23/21 10:38 AM
 * Last modified 2/23/21 10:37 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder

object ProductJsonDataStructure {
    const val ProductId = "id"
    const val ProductName = "name"
    const val ProductShortDescription = "short_description"
    const val ProductDescription = "description"
    const val ProductPrice = "regular_price"
    const val ProductOnSale = "on_sale"
    const val ProductSalePrice = "sale_price"
    const val ProductLink = "external_url"
    const val ProductImages = "images"
    const val ProductTags = "tags"
    const val ProductCategories = "categories"
    const val ProductRalated = "related_ids"
}

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