/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 2:15 AM
 * Last modified 7/25/20 9:50 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostsDataModel::class], version = 100000, exportSchema = false)
abstract class PostsDataInterface : RoomDatabase() {
    abstract fun initDataAccessObject(): PostsDataDAO
}