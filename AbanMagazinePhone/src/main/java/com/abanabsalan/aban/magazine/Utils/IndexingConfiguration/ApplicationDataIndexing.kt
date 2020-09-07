/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/7/20 4:38 AM
 * Last modified 9/7/20 4:37 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.IndexingConfiguration

import android.net.Uri
import android.util.Log
import com.abanabsalan.aban.magazine.Utils.BlogContent.LanguageUtils
import com.google.firebase.appindexing.Action
import com.google.firebase.appindexing.FirebaseAppIndex
import com.google.firebase.appindexing.FirebaseUserActions
import com.google.firebase.appindexing.Indexable
import com.google.firebase.appindexing.builders.Actions

class ApplicationDataIndexing {

    companion object {
        val BASE_URI: Uri = Uri.parse("https://www.abanabsalan.com/indexing")
    }

    fun insert(indexLink: String, indexId: String, indexTitle: String, indexImage: String, indexDescription: String) {

        val articleToIndex: Indexable = Indexable.Builder()
            .setName(indexTitle)
            .setImage(indexImage)
            .setDescription(indexDescription)
            .setSameAs(indexLink)
            .setKeywords(createKeywords(indexTitle))
            .setUrl(ApplicationDataIndexing.BASE_URI.buildUpon().appendPath(indexId).build().toString())
            .build()

        FirebaseAppIndex.getInstance().update(articleToIndex)
            .addOnSuccessListener {
                Log.d(this@ApplicationDataIndexing.javaClass.simpleName, " Indexed Successfully | ${articleToIndex.toString()}")

                FirebaseUserActions.getInstance()
                    .start(getAction(indexTitle, ApplicationDataIndexing.BASE_URI.buildUpon().appendPath(indexTitle).build().toString()))
                    .addOnSuccessListener {
                        Log.d(this@ApplicationDataIndexing.javaClass.simpleName, " Indexed Action Successfully | ${articleToIndex.toString()}")


                    }.addOnFailureListener { e ->
                        e.printStackTrace()


                    }

            }.addOnFailureListener { e ->
                e.printStackTrace()

            }

    }

    fun endIndexAppInfo(titleForAction: String, uriForAction: String) {
        try {
            FirebaseUserActions.getInstance().end(getAction(titleForAction, uriForAction))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getAction(titleForAction: String, uriForAction: String): Action {

        return Actions.newView(titleForAction, uriForAction)
    }

    private fun createKeywords(postTitle: String) : String {

        val language = LanguageUtils()

        val allWords = postTitle.split(" ")

        val hashtagsText: StringBuilder = StringBuilder()

        allWords.forEach {

            if (language.checkRtl(it)) {

                hashtagsText.append("${it}")

            } else {

                hashtagsText.append("${it}")

            }
        }

        return hashtagsText.toString()
    }

}