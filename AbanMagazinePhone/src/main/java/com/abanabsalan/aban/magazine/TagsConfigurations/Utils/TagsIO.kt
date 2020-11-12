/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/12/20 6:05 AM
 * Last modified 11/12/20 6:05 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.TagsConfigurations.Utils

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences

class TagsIO(val context: Context) {

    private val savePreferences: SavePreferences = SavePreferences(context)
    private val readPreferences: ReadPreferences = ReadPreferences(context)

    fun saveTagsDatabase(allTags: String) {

        allTags.split(",").forEach { tag ->

            val tagCount = readPreferences.readPreference("Tags", tag, 0)

            savePreferences.savePreference("Tags", tag, (tagCount + 1))

        }

    }

    fun prepareRecommendedTags() : String {

        val sharedPreferences = context.getSharedPreferences("Tags", Context.MODE_PRIVATE)

        val allSavedTagsValue = sharedPreferences.all.entries.sortedByDescending {

            it.value.toString().toInt()
        }

        val sliceAllSavedTagsValue = allSavedTagsValue.subList(0, if(allSavedTagsValue.size > 3) { 3 } else { allSavedTagsValue.size })

        val tagsCsv = StringBuilder()
        sliceAllSavedTagsValue.forEach {

            tagsCsv.append("${it.key},")

        }

        val finalCharIndex = tagsCsv.toString().length
        val beforeFinalCharIndex = tagsCsv.toString().length - 1

        return tagsCsv.toString().replaceRange(beforeFinalCharIndex, finalCharIndex, "")
    }

}