/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 12:10 AM
 * Last modified 8/12/20 11:59 PM
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