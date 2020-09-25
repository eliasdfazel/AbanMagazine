/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/25/20 11:27 AM
 * Last modified 9/25/20 11:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.Utils

import com.abanabsalan.aban.magazine.ProductShowcaseConfigurations.DataHolder.ProductShowcase

fun notProductShowcaseElement(elementId: String) : Boolean {

    var notShowcaseElement = false

    if (elementId != ProductShowcase.ProductShowcase
        && elementId != ProductShowcase.ProductLink
        && elementId != ProductShowcase.ProductTitle
        && elementId != ProductShowcase.ProductBrand
        && elementId != ProductShowcase.ProductDescription
        && elementId != ProductShowcase.ProductImage) {

        notShowcaseElement = true

    }

    return notShowcaseElement
}