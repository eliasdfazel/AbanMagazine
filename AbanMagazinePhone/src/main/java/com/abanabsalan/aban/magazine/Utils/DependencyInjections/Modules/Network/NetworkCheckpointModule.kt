/*
 * Copyright © 2022 By Geeks Empire.
 *
 * Created by Elias Fazel on 4/25/22, 5:58 AM
 * Last modified 6/8/21, 9:17 AM
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