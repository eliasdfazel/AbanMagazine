/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 9:31 AM
 * Last modified 4/25/22, 9:14 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.Adapter

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abanabsalan.aban.magazine.databinding.AllCategoryPostsItemBinding
import net.geekstools.imageview.customshapes.ShapesImage

class AllCategoryPostsViewHolder (allCategoryPostsItemBinding: AllCategoryPostsItemBinding) : RecyclerView.ViewHolder(allCategoryPostsItemBinding.root) {
    val rootViewItem: ConstraintLayout = allCategoryPostsItemBinding.rootViewItem
    val postFeatureImageView: ShapesImage = allCategoryPostsItemBinding.postFeatureImageView
    val postTitleView: TextView = allCategoryPostsItemBinding.postTitleView
    val postExcerptView: TextView = allCategoryPostsItemBinding.postExcerptView
    val shareIcon: AppCompatImageView = allCategoryPostsItemBinding.shareIcon
}