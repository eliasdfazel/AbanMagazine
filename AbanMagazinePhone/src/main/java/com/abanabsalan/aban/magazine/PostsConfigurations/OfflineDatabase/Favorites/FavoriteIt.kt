/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/31/20 5:54 AM
 * Last modified 7/31/20 5:53 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.Favorites

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
    }

    lateinit var favoriteInterface: FavoriteInterface

    fun saveAsFavorite(postId: String) {

        val savePreferences: SavePreferences = SavePreferences(context)

        savePreferences.savePreference(FavoriteIt.PreferenceName, postId, true)

        favoriteInterface.favoritedIt()

    }

    fun deleteFavorite(postId: String) {

        val savePreferences: SavePreferences = SavePreferences(context)

        savePreferences.savePreference(FavoriteIt.PreferenceName, postId, false)

        favoriteInterface.unfavoritedIt()

    }

    fun isFavorited(postId: String) : Boolean {

        val readPreferences: ReadPreferences = ReadPreferences(context)

        return readPreferences.readPreference(FavoriteIt.PreferenceName, postId, false)

    }

}