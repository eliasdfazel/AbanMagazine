/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 2:44 PM
 * Last modified 6/28/20 2:33 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.app.Application
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.DaggerDependencyGraph
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.DependencyGraph

class AbanMagazinePhoneApplication : Application() {

    val dependencyGraph: DependencyGraph by lazy {
        DaggerDependencyGraph.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }
}