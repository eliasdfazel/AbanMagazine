/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/23/20 7:09 AM
 * Last modified 8/23/20 7:08 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.IndexingConfiguration

import android.net.Uri
import com.google.android.gms.tasks.Task

class ApplicationIndexing {

    fun insert() {

    }

    fun IndexAppInfoShortcuts(contentAppIndex: String) {

        val BASE_URL = Uri.parse("https://www.geeksempire.net/createshortcuts.html/")

        val articleToIndex: Indexable = Builder()
            .setName(contentAppIndex)
            .setUrl(BASE_URL.buildUpon().appendPath(contentAppIndex).build().toString())
            .build()

        val updateTask: Task<Void> = FirebaseAppIndex.getInstance().update(articleToIndex)

        updateTask.addOnSuccessListener {


        }.addOnFailureListener { e ->
            e.printStackTrace()

        }

        val startTask: Task<Void> = FirebaseUserActions.getInstance()
            .start(
                getAction(
                    contentAppIndex,
                    BASE_URL.buildUpon().appendPath(contentAppIndex).build().toString()
                )
            )

        startTask.addOnSuccessListener {


        }.addOnFailureListener { e ->
            e.printStackTrace()

        }

    }

    fun endIndexAppInfo() {
        try {
            FirebaseUserActions.getInstance().end(null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getAction(titleForAction: String, urlForAction: String): Action? {

        return Actions.newView(titleForAction, urlForAction)
    }

}