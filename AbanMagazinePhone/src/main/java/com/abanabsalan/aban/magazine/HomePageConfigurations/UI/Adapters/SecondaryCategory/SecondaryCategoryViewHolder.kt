/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SecondaryCategory

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.HomePageSecondaryCategoriesItemBinding
import net.geekstools.imageview.customshapes.ShapesImage

class SecondaryCategoryViewHolder (homePageSecondaryCategoriesItemBinding: HomePageSecondaryCategoriesItemBinding) : RecyclerView.ViewHolder(homePageSecondaryCategoriesItemBinding.root) {
    val rootViewItem: ConstraintLayout = homePageSecondaryCategoriesItemBinding.rootViewItem
    val categoryFeaturedImage: ShapesImage = homePageSecondaryCategoriesItemBinding.categoryFeaturedImage
    val categoryNameView: TextView = homePageSecondaryCategoriesItemBinding.categoryNameView
}