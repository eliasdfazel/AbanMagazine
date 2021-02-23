/*
 * Copyright © 2021 By Geeks Empire.
 *
 * Created by Elias Fazel on 2/23/21 10:43 AM
 * Last modified 2/23/21 10:43 AM
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
    /**
     * Json Array
     **/
    const val ProductImages = "images"
    /**
     * Json Array
     **/
    const val ProductTags = "tags"
    /**
     * Json Array
     **/
    const val ProductCategories = "categories"
    /**
     * Json Array
     **/
    const val ProductRelated = "related_ids"
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

data class ProductJsonDataStructureItem (
    var productId: String,
    var productName: String,
    var productShortDescription: String,
    var productDescription: String,
    var productPrice: String,
    var productOnSale: String,
    var productSalePrice: String,
    var productLink: String,
    var productImages: String,
    var productTags: String,
    var productCategories: String,
    var productRelated: String,
)