/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/16/20 5:54 AM
 * Last modified 8/16/20 5:54 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.PopupShortcuts

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesItemData
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts
import com.abanabsalan.aban.magazine.Utils.UI.Images.drawableToBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.*

data class PopupShortcutsItems(var shortcutIndex: Int,
                               var shortcutId: String,
                               var shortcutLink: String?,
                               var shortcutLabel: String,
                               var shortcutDescription: String?,
                               var shortcutIcon: Icon)

class PopupShortcutsCreator (private val context: AppCompatActivity) {

    object TYPE {
        const val Category: String = "Category"
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun buildAppShortcut(listOfShortcutsRawData: ArrayList<Any>, shortcutType: String) = CoroutineScope(Dispatchers.IO).async {

        delay(3000)

        when (shortcutType) {
            PopupShortcutsCreator.TYPE.Category -> {

                listOfShortcutsRawData as ArrayList<CategoriesItemData>

                val listOfShortcuts: ArrayList<PopupShortcutsItems> = ArrayList<PopupShortcutsItems>()

                listOfShortcutsRawData.forEachIndexed { index, categoriesItemData ->

                    Glide.with(context)
                        .asDrawable()
                        .load("${CategoriesDataParameters.CategoryParameters.CategoryFeaturedImageBaseLink}${categoriesItemData.categoryId}")
                        .transform(CenterCrop(), CircleCrop())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                                return true
                            }

                            override fun onResourceReady(drawable: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                                drawable?.let {

                                    addCategories(
                                        PopupShortcutsItems(
                                            shortcutIndex = index,
                                            shortcutId = categoriesItemData.categoryId,
                                            shortcutLink = categoriesItemData.categoryLink,
                                            shortcutLabel = categoriesItemData.categoryName,
                                            shortcutDescription = categoriesItemData.categoryDescription,
                                            shortcutIcon = Icon.createWithBitmap(drawableToBitmap(drawable))
                                        )
                                    )

                                }

                                return true
                            }

                        })
                        .submit()

                }

            }
            else -> {

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    private fun addCategories(listOfShortcuts: PopupShortcutsItems) = CoroutineScope(SupervisorJob() + Dispatchers.IO).async {

        val shortcutManager: ShortcutManager = context.getSystemService(ShortcutManager::class.java) as ShortcutManager

        if (listOfShortcuts.shortcutIndex == 0) {
            shortcutManager.removeAllDynamicShortcuts()
        }

        val shortcutsInformation: ArrayList<ShortcutInfo> = ArrayList<ShortcutInfo>()
        shortcutsInformation.clear()

        val shortcutsHomeLauncherCategories: HashSet<String> = HashSet<String>()
        shortcutsHomeLauncherCategories.addAll(arrayOf("Fashion", "Beauty", "News", "Magazine"))

        val intent = Intent(context, AllCategoryPosts::class.java)
        intent.action = "POPUP_SHORTCUTS_CATEGORIES"
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(CategoriesDataParameters.CategoryParameters.CategoryId, listOfShortcuts.shortcutId)
        intent.putExtra(CategoriesDataParameters.CategoryParameters.CategoryLink, listOfShortcuts.shortcutLink)
        intent.putExtra(CategoriesDataParameters.CategoryParameters.CategoryName, listOfShortcuts.shortcutLabel)
        intent.putExtra(CategoriesDataParameters.CategoryParameters.CategoryDescription, listOfShortcuts.shortcutDescription)

        val shortcutInfo = ShortcutInfo.Builder(context, listOfShortcuts.shortcutId)
            .setShortLabel(listOfShortcuts.shortcutLabel)
            .setLongLabel(listOfShortcuts.shortcutLabel)
            .setIcon(listOfShortcuts.shortcutIcon)
            .setIntent(intent)
            .setCategories(shortcutsHomeLauncherCategories)
            .setRank(listOfShortcuts.shortcutIndex)
            .build()

        shortcutsInformation.add(shortcutInfo)

        shortcutManager.addDynamicShortcuts(shortcutsInformation)

    }

}