/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/25/20 8:13 PM
 * Last modified 7/25/20 8:08 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface PostsDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewPostData(vararg arrayOfPostsDataModels: PostsDataModel)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWidgetData(vararg arrayOfPostsDataModels: PostsDataModel)


    @Delete
    suspend fun delete(postsDataModel: PostsDataModel)


    @Query("SELECT * FROM PostsOfflineDatabase ORDER BY PostId ASC")
    suspend fun getAllPostsData(): Flowable<List<PostsDataModel>>

    @Query("SELECT * FROM PostsOfflineDatabase WHERE PostCategories LIKE :postCategories")
    fun loadPostsDataByCategory(postCategories: String?): Flowable<List<PostsDataModel>>

    @Query("SELECT * FROM PostsOfflineDatabase WHERE PostTags LIKE :postTags")
    fun loadPostsDataByTag(postTags: String?): Flowable<List<PostsDataModel>>
}