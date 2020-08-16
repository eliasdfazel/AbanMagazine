/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/16/20 3:33 AM
 * Last modified 8/16/20 3:33 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.PopupShortcuts

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.abanabsalan.aban.magazine.CategoriesConfigurations.DataHolder.CategoriesDataParameters
import com.abanabsalan.aban.magazine.CategoriesConfigurations.UI.AllCategoryPosts

data class PopupShortcutsItems(var shortcutId: String, var shortcutLabel: String, var shortcutIcon: Icon)

class PopupShortcutsCreator (private val context: AppCompatActivity) {

    /**
     * Key Of Category Id
     * Icon Of Category Featured Image
     **/
    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun add(listOfShortcuts: ArrayList<PopupShortcutsItems>) {

        val shortcutManager: ShortcutManager = context.getSystemService(ShortcutManager::class.java) as ShortcutManager

        shortcutManager.removeAllDynamicShortcuts()

        val shortcutsInformation: ArrayList<ShortcutInfo> = ArrayList<ShortcutInfo>()
        shortcutsInformation.clear()

        listOfShortcuts.forEachIndexed { index, popupShortcutsItems ->

            if (index <= shortcutManager.maxShortcutCountPerActivity) {

                val intent = Intent(context, AllCategoryPosts::class.java)
                intent.action = "MEDIATED_ACTIVITY_PRO"
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra(CategoriesDataParameters.CategoryParameters.CategoryId, popupShortcutsItems.shortcutId)

                val shortcutInfo = ShortcutInfo.Builder(context, popupShortcutsItems.shortcutId)
                    .setShortLabel(popupShortcutsItems.shortcutLabel)
                    .setLongLabel(popupShortcutsItems.shortcutLabel)
                    .setIcon(popupShortcutsItems.shortcutIcon)
                    .setIntent(intent)
                    .setRank(index)
                    .build()

                shortcutsInformation.add(shortcutInfo)

            }

        }

        shortcutManager.addDynamicShortcuts(shortcutsInformation)

    }

}