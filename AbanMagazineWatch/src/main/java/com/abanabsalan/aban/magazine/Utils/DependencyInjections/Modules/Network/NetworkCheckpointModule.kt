/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/13/20 2:15 AM
 * Last modified 6/28/20 2:44 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.DependencyInjections.Modules.Network

import com.abanabsalan.aban.magazine.Utils.Network.InterfaceNetworkCheckpoint
import com.abanabsalan.aban.magazine.Utils.Network.NetworkCheckpoint
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkCheckpointModule {

    @Binds
    abstract fun provideNetworkCheckpoint(networkCheckpoint: NetworkCheckpoint/*This is Instance Of Return Type*/): InterfaceNetworkCheckpoint
}