/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.FavoritedPosts.Utils

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Preferences.ReadPreferences
import com.abanabsalan.aban.magazine.Utils.Preferences.SavePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

interface FavoriteInterface {
    fun favoritedIt() = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {}
    fun unfavoritedIt() = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {}
}

class FavoriteIt (private val context: Context) {

    companion object {
        const val PreferenceName: String = ".FavoriteIt"

        var FavoriteDataChanged: Boolean = false
    }

    lateinit var favoriteInterface: FavoriteInterface

    fun saveAsFavorite(postId: String) {

        val savePreferences: SavePreferences = SavePreferences(context)

        savePreferences.savePreference(PreferenceName, postId, true)

        favoriteInterface.favoritedIt()

    }

    fun deleteFavorite(postId: String) {

        val savePreferences: SavePreferences = SavePreferences(context)

        savePreferences.removePrefrenceItem(PreferenceName, postId)

        favoriteInterface.unfavoritedIt()

    }

    fun isFavorited(postId: String) : Boolean {

        val readPreferences: ReadPreferences = ReadPreferences(context)

        return readPreferences.readPreference(PreferenceName, postId, false)

    }

    fun getAllFavoritedPosts(): MutableMap<String, *>? {

        return context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE).all

    }

}