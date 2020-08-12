/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 5:51 AM
 * Last modified 8/12/20 5:45 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class EntryConfiguration : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_configuration_view)

        // Enables Always-on
        setAmbientEnabled()
    }
}
