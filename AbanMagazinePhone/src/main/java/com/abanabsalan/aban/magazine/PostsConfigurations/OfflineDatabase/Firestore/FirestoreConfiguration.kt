/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 11/20/20 9:00 AM
 * Last modified 11/20/20 8:54 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.Firestore

import android.content.Context
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.System.SystemInformation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import java.util.*

class FirestoreConfiguration (private val context: Context) {

    private val networkCheckpoint: NetworkCheckpoint = NetworkCheckpoint(context)

    private val systemInformation: SystemInformation = SystemInformation(context)

    fun initialize() : FirebaseFirestore {

        val firebaseFirestore = Firebase.firestore

        val firebaseFirestoreSettings = firestoreSettings {
            isPersistenceEnabled = true
            cacheSizeBytes = FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED
        }

        firebaseFirestore.firestoreSettings = firebaseFirestoreSettings

        if (networkCheckpoint.networkConnectionVpn()) {

            firebaseFirestore.enableNetwork().addOnSuccessListener {

            }.addOnFailureListener {

            }

        } else if (systemInformation.getCountryIso().toUpperCase(Locale.getDefault()) == "IR"
            || systemInformation.getCountryIso() == "Undefined") {

            firebaseFirestore.disableNetwork().addOnSuccessListener {

            }.addOnFailureListener {

            }

        } else {

            firebaseFirestore.enableNetwork().addOnSuccessListener {

            }.addOnFailureListener {

            }

        }

        return firebaseFirestore
    }

    fun favoritedPostsCollectionPath(emailAddress: String) : String {


        return "${emailAddress}" + "/" + "Favorited" + "/" + "Posts"
    }

    fun favoritedPostDatabasePath(emailAddress: String, favoritedPostId: String) : String {


        return "${emailAddress}" + "/" + "Favorited" + "/" + "Posts" + "/" + "${favoritedPostId}"
    }

}