/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 6/8/21, 9:17 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Utils

import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.ProductShowcase

fun notProductShowcaseElement(elementId: String) : Boolean {

    var notShowcaseElement = false

    if (!elementId.contains(ProductShowcase.ProductShowcase)
        && elementId != ProductShowcase.ProductLink
        && elementId != ProductShowcase.ProductTitle
        && elementId != ProductShowcase.ProductBrand
        && elementId != ProductShowcase.ProductDescription
        && elementId != ProductShowcase.ProductImage) {

        notShowcaseElement = true

    }

    return notShowcaseElement
}