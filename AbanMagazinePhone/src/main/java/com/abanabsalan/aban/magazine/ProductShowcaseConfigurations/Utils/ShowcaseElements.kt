/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/28/20 12:37 PM
 * Last modified 9/28/20 12:34 PM
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