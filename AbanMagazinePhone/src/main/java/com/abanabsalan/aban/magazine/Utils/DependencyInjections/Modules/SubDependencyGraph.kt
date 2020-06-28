/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 2:44 PM
 * Last modified 6/28/20 2:01 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.DependencyInjections.Modules

import com.abanabsalan.aban.magazine.Utils.DependencyInjections.SubComponents.NetworkSubDependencyGraph
import dagger.Module

@Module(subcomponents = [NetworkSubDependencyGraph::class])
class SubDependencyGraphs