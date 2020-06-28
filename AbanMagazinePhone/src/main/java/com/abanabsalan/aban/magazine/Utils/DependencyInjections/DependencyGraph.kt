/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 2:44 PM
 * Last modified 6/28/20 1:53 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.DependencyInjections

import android.content.Context
import com.abanabsalan.aban.magazine.EntryConfiguration
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.Modules.Network.NetworkCheckpointModule
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.Modules.SubDependencyGraphs
import com.abanabsalan.aban.magazine.Utils.DependencyInjections.SubComponents.NetworkSubDependencyGraph
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [NetworkCheckpointModule::class, SubDependencyGraphs::class])
interface DependencyGraph {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DependencyGraph
    }

    fun subDependencyGraph(): NetworkSubDependencyGraph.Factory

    fun inject(entryConfiguration: EntryConfiguration)
}
