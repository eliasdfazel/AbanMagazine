/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/4/20 3:34 PM
 * Last modified 7/4/20 3:34 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.HomePageConfigurations.UI.Adapters.SpecificCategory

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_page_specific_category_item.view.*
import net.geekstools.imageview.customshapes.ShapesImage

class SpecificCategoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val rootViewItem: ConstraintLayout = view.rootViewItem
    val postFeatureImageView: ShapesImage = view.postFeatureImageView
    val postExcerptView: TextView = view.postExcerptView
    val postTitleView: TextView = view.postTitleView
}