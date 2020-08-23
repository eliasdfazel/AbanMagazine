/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/23/20 7:10 AM
 * Last modified 8/23/20 7:10 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.IndexingConfiguration

import android.net.Uri

class ApplicationIndexing {

    fun insert() {

    }

    fun IndexAppInfoShortcuts(contentAppIndex: String) {

        val BASE_URL = Uri.parse("https://www.geeksempire.net/createshortcuts.html/")

        val articleToIndex: Indexable = Builder()
            .setName(contentAppIndex)
            .setUrl(BASE_URL.buildUpon().appendPath(contentAppIndex).build().toString())
            .build()

        FirebaseAppIndex.getInstance().update(articleToIndex)
            .addOnSuccessListener {


            }.addOnFailureListener { e ->
                e.printStackTrace()

            }

        FirebaseUserActions.getInstance()
            .start(
                getAction(
                    contentAppIndex,
                    BASE_URL.buildUpon().appendPath(contentAppIndex).build().toString()
                )
            ).addOnSuccessListener {


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