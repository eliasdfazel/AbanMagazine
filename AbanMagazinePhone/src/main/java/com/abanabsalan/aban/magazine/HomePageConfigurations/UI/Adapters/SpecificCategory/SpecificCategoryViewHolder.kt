/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:25 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.HomePageSpecificCategoryItemBinding
import net.geekstools.imageview.customshapes.ShapesImage

class SpecificCategoryViewHolder (homePageSpecificCategoryItemBinding: HomePageSpecificCategoryItemBinding) : RecyclerView.ViewHolder(homePageSpecificCategoryItemBinding.root) {
    val rootViewItem: ConstraintLayout = homePageSpecificCategoryItemBinding.rootViewItem
    val postFeatureImageView: ShapesImage = homePageSpecificCategoryItemBinding.postFeatureImageView
    val postExcerptView: TextView = homePageSpecificCategoryItemBinding.postExcerptView
    val postTitleView: TextView = homePageSpecificCategoryItemBinding.postTitleView
}