/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
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
