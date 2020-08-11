/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/11/20 1:47 AM
 * Last modified 8/11/20 1:46 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.app.Application
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.DaggerDependencyGraph
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.DependencyGraph
import com.google.firebase.analytics.FirebaseAnalytics

class AbanMagazinePhoneApplication : Application() {

    val dependencyGraph: DependencyGraph by lazy {
        DaggerDependencyGraph.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        val firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)

        firebaseAnalytics.setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)

    }
}