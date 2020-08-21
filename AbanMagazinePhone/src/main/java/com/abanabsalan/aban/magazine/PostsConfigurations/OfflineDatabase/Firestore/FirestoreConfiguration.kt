/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/21/20 3:38 AM
 * Last modified 8/21/20 3:38 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.PostsConfigurations.OfflineDatabase.Firestore

import android.content.Context
import com.abanabsalan.aban.magazine.BuildConfig
import com.abanabsalan.aban.magazine.Utils.System.SystemInformation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestoreSettings
import java.util.*

class FirestoreConfiguration (private val context: Context) {

    private val systemInformation: SystemInformation = SystemInformation(context)

    fun initialize() : FirebaseFirestore {

        val firebaseFirestore = FirebaseFirestore.getInstance()

        val firebaseFirestoreSettings = firestoreSettings {
            isPersistenceEnabled = true
            cacheSizeBytes = FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED
        }

        firebaseFirestore.firestoreSettings = firebaseFirestoreSettings

        if (BuildConfig.DEBUG) {

            firebaseFirestore.enableNetwork().addOnSuccessListener {

            }.addOnFailureListener {

            }

        } else {

            if (systemInformation.getCountryIso().toUpperCase(Locale.getDefault()) == "IR"
                || systemInformation.getCountryIso() == "Undefined") {

                firebaseFirestore.disableNetwork().addOnSuccessListener {

                }.addOnFailureListener {

                }

            } else {

                firebaseFirestore.enableNetwork().addOnSuccessListener {

                }.addOnFailureListener {

                }

            }

        }

        return firebaseFirestore
    }

}